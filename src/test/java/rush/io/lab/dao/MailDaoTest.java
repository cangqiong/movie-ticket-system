package rush.io.lab.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author cang
 * @create_time 2017/1/7 13:23
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-mail.xml"})
public class MailDaoTest {

    @Resource
    private MailDao mailDao;
    private String toEmail = "cang19910623@163.com";

    @Test
    public void send() throws Exception {
        String title = "test";
        String content = "cotent";
        mailDao.send(toEmail, title, content);
    }

}