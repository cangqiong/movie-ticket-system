package rush.io.lab.service;

import java.util.Map;

/**
 * 邮件服务类
 *
 * @author cang
 * @create_time 2017-01-07 16:35
 */
public interface MailService {

    /**
     * 发送邮件
     *
     * @param userId
     * @param title
     * @param content
     */
    void sendMail(String userId, String title, String content);

    /**
     * 发送成功抢到票的信息
     *
     * @param userId
     * @param ticketId
     */
    void sendSuccessGrapTicketMail(String userId, long ticketId);
}
