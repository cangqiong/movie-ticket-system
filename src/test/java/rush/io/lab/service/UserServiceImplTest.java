package rush.io.lab.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author cang
 * @create_time 2017/1/7 11:58
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class UserServiceImplTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserService userService;

    private String mail = "test2@mail.com";
    private String name = "test2";
    private String pass = "123456";

    @Test
    public void loginByUsername() throws Exception {
        userService.loginByUsername(name, mail);
    }

    @Test
    public void loginByMail() throws Exception {

    }

    @Test
    public void registerByMail() throws Exception {
        Map<String, Object> result = userService.registerByMail(mail, name, pass);
        assertEquals("success",result.get("status"));
    }

    @Test
    public void resetPasswd() throws Exception {
        Map<String, Object> result = userService.resetPasswd(name, "ps");
        assertEquals("success",result.get("status"));
    }

}