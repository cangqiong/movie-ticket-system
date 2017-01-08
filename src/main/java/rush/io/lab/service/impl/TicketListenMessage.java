package rush.io.lab.service.impl;

import java.io.Serializable;
import java.util.Date;

import rush.io.lab.dao.GrabTicketRecordDao;
import rush.io.lab.dao.TicketDao;
import rush.io.lab.dao.cache.RedisDao;
import rush.io.lab.dto.TicketRequest;
import rush.io.lab.entity.GrabTicketRecord;
import rush.io.lab.entity.Ticket;
import rush.io.lab.exception.RepeatGrabTicketException;
import rush.io.lab.exception.TicketException;
import rush.io.lab.exception.TicketSoldOutException;
import rush.io.lab.service.MailService;
import rush.io.lab.util.GrapTicketStateEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 消息处理类
 */
@Component(value = "messageDelegateListener")
public class TicketListenMessage {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private GrabTicketRecordDao grabTicketRecordDao;

    @Resource
    private TicketDao ticketDao;

    @Resource
    private RedisDao redisDao;

    @Resource
    private MailService mailService;

    public void handleMessage(Serializable message) {
        if (message instanceof TicketRequest) {
            TicketRequest request = (TicketRequest) message;
            // 验证数据格式是否错误
            if (request.getUserId() == null || request.getNumber() != 1) {
                // 直接抛弃错误格式信息
                logger.error("数据格式错误");
                return;
            }
            String userId = request.getUserId();
            long ticketId = request.getTicketId();

            // 判断用户是否已经购买了该电影票
            if (redisDao.getGrapTicketInfo(userId, ticketId)) {
                redisDao.setGrapTicketStatus(userId, ticketId, GrapTicketStateEnum.REPEAT_KILL);
            } else {
                // 判断是否票已售完,已加锁
                int count = redisDao.getTicketCount(ticketId);
                if (count == -1) {
                    Ticket ticket = ticketDao.findOne(ticketId);
                    redisDao.setTicketCount(ticketId, ticket.getNumber());
                    count = ticket.getNumber();
                }
                if (count == 0) {
                    redisDao.setGrapTicketStatus(userId, ticketId, GrapTicketStateEnum.END);
                } else {

                    // 执行抢票
                    try {
                        executeGrapTicket(userId, ticketId);
                        redisDao.setGrapTicketStatus(userId, ticketId, GrapTicketStateEnum.SUCCESS);
                        redisDao.reduceTicketCount(ticketId);
                        // 抢票成功，发送邮件
                        mailService.sendSuccessGrapTicketMail(userId, ticketId);
                    } catch (TicketSoldOutException e1) {
                        logger.error(e1.getMessage(), e1);
                        redisDao.setGrapTicketStatus(userId, ticketId, GrapTicketStateEnum.END);
                    } catch (RepeatGrabTicketException e2) {
                        logger.error(e2.getMessage(), e2);
                        redisDao.setGrapTicketStatus(userId, ticketId, GrapTicketStateEnum.REPEAT_KILL);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        redisDao.setGrapTicketStatus(userId, ticketId, GrapTicketStateEnum.INNER_ERROR);
                    }
                }
            }

        } else {
            // 直接抛弃其他信息
            logger.error("数据格式错误");
        }
    }

    @Transactional
    public void executeGrapTicket(String userId, long ticketId) throws TicketException, RepeatGrabTicketException, TicketSoldOutException {
        // 判断是否重复抢票
        GrabTicketRecord record = grabTicketRecordDao.findByTicketIdAndUserId(ticketId, userId);

        if (record != null) {
            throw new RepeatGrabTicketException("repeated grap ticket");
        } else {
            // 保存购买记录
            record = new GrabTicketRecord();
            record.setTicketId(ticketId);
            record.setUserId(userId);
            record.setState(1);
            grabTicketRecordDao.save(record);
            Date nowTime = new Date();

            // 减库存
            int updataCount = ticketDao.grabTicket(ticketId, nowTime);
            if (updataCount <= 0) {
                // 电影票已售完
                throw new TicketSoldOutException("grap ticket is over");
            }
        }
    }
}