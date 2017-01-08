package rush.io.lab.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author cang
 * @create_time 2017-01-07 13:08
 */
@Repository
public class MailDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private JavaMailSenderImpl mailSender;

    /**
     * 检测邮箱地址是否合法
     *
     * @param email
     * @return
     */
    public boolean isEmail(String email) {
        if (null == email || "".equals(email)) return false;
        //简单匹配
        //Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}");
        //复杂匹配
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 发送简单文本信息
     *
     * @param to
     * @param subject
     * @param text
     */
    public void send(String to, String subject, String text) {
        // 构建简单邮件对象
        SimpleMailMessage mail = new SimpleMailMessage();
        // 设定邮件参数
        mail.setFrom(mailSender.getUsername());
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(text);
        try {
            mailSender.send(mail);
        } catch (MailException e) {
            logger.error(e.getMessage());
        }
    }


    /**
     * 发送带附件的邮件，可指定附件名
     *
     * @param to
     * @param subject
     * @param text
     * @param fileName
     * @param file
     */
    public void send(String to, String subject, String text,
                     String fileName, File file) {
        //使用JavaMail的MimeMessage，支付更加复杂的邮件格式和内容
        MimeMessage msg = mailSender.createMimeMessage();
        try {
            //创建MimeMessageHelper对象，处理MimeMessage的辅助类
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            //使用辅助类MimeMessage设定参数
            helper.setFrom(mailSender.getUsername());
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            //加入附件
            if (file != null) helper.addAttachment(fileName, file);
            //发送邮件
            mailSender.send(msg);
        } catch (MessagingException e) {
            logger.error(e.getMessage());
        }
    }

}
