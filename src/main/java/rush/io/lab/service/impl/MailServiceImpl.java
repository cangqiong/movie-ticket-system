package rush.io.lab.service.impl;

import rush.io.lab.dao.MailDao;
import rush.io.lab.dao.TicketDao;
import rush.io.lab.dao.UserInfoDao;
import rush.io.lab.entity.Ticket;
import rush.io.lab.entity.User;
import rush.io.lab.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 邮件服务实现类
 *
 * @author cang
 * @create_time 2017-01-07 16:37
 */
@Service
public class MailServiceImpl implements MailService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MailDao mailDao;

    @Resource
    private UserInfoDao userInfoDao;

    @Resource
    private TicketDao ticketDao;

    @Override
    public void sendMail(String userId, String title, String content) {
        User user = userInfoDao.findOne(userId);
        if (user == null || user.getMail() == null) {
            logger.error("没有用户的邮箱信息！");
        } else {
            mailDao.send(user.getMail(), title, content);
        }
    }

    @Override
    public void sendSuccessGrapTicketMail(String userId, long ticketId) {
        Ticket ticket = ticketDao.findOne(ticketId);

        String title = "抢票信息";
        StringBuilder sb = new StringBuilder();
        sb.append("恭喜你，抢到票").append(ticket.getName()).append("1张");

        sendMail(userId, title, sb.toString());
    }
}
