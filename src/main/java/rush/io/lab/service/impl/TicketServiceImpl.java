package rush.io.lab.service.impl;

import rush.io.lab.dao.GrabTicketRecordDao;
import rush.io.lab.dao.MailDao;
import rush.io.lab.dao.TicketDao;
import rush.io.lab.dao.cache.RedisDao;
import rush.io.lab.dto.Exposer;
import rush.io.lab.dto.GrapTicketExecution;
import rush.io.lab.entity.GrabTicketRecord;
import rush.io.lab.entity.Ticket;
import rush.io.lab.exception.TicketSoldOutException;
import rush.io.lab.util.GrapTicketStateEnum;
import rush.io.lab.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import rush.io.lab.exception.RepeatGrabTicketException;
import rush.io.lab.exception.TicketException;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 抢票服务实现类
 *
 * @author cang
 * @create_time 2017-01-05 23:16
 */
@Service
public class TicketServiceImpl implements TicketService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private GrabTicketRecordDao grabTicketRecordDao;

    @Resource
    private TicketDao ticketDao;

    @Resource
    private RedisDao redisDao;

    @Resource
    private MailDao mailDao;

    // 加入混淆的盐值，用于混淆md5
    private final String slat = "df34343dfdf4df";

    @Override
    public List<Ticket> getTicketList() {
        return ticketDao.findAll();
    }

    @Override
    public Ticket getTicketById(long tickedId) {
        return ticketDao.findOne(tickedId);
    }

    @Override
    public Exposer exportGrabTicketUrl(long tickedId) {
        Ticket ticket = ticketDao.findOne(tickedId);
        if (ticket == null) {
            return new Exposer(false, tickedId);
        }
        long startTime = ticket.getStartTime().getTime();
        long endTime = ticket.getEndTime().getTime();

        // 系统当前时间
        Date now = new Date();
        if (now.getTime() < startTime || now.getTime() > endTime) {
            return new Exposer(false, tickedId, now.getTime(), startTime, endTime);
        }
        // 对字符串加密，过程不可逆
        String md5 = getMD5(tickedId);
        return new Exposer(true, md5, tickedId);
    }

    private String getMD5(long tickedId) {
        String base = tickedId + "/" + slat;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    @Override
    @Transactional
    public GrapTicketExecution executeGrabTicket(long ticketId, String userId, String md5) throws TicketException, RepeatGrabTicketException, TicketSoldOutException {
        // 判断请求URL
        if (md5 == null || !md5.equals(getMD5(ticketId))) {
            throw new TicketException("movie data rewrite");
        }

        // 执行秒杀逻辑：减库存 + 记录购买行为
        Date nowTime = new Date();
        // 记录购买行为
        GrabTicketRecord record = grabTicketRecordDao.findByTicketIdAndUserId(ticketId, userId);

        // 判断是否重复抢票
        if (record != null) {
            throw new RepeatGrabTicketException("repeated grap ticket");
        } else {
            record = new GrabTicketRecord();
            record.setTicketId(ticketId);
            record.setUserId(userId);
            record.setState(1);
            grabTicketRecordDao.save(record);

            // 减库存
            int updataCount = ticketDao.grabTicket(ticketId, nowTime);
            if (updataCount <= 0) {
                // 电影票已售完
                throw new TicketSoldOutException("grap ticket is over");
            } else {
                // 秒杀成功,commit
                GrabTicketRecord ticketRecord = grabTicketRecordDao.findByTicketIdAndUserId(ticketId, userId);

                return new GrapTicketExecution(ticketId, GrapTicketStateEnum.SUCCESS, ticketRecord);
            }
        }
    }
}
