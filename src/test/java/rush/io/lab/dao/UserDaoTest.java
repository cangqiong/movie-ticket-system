package rush.io.lab.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import rush.io.lab.entity.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author cang
 * @create_time 2017/1/7 10:45
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class UserDaoTest {

    @Resource
    private UserInfoDao userInfoDao;

    private String mail = "test@mail.com";
    private String name = "test";
    private String pass = "123456";

    @Test
    public void save() {
        User user = new User();
        user.setMail(mail);
        user.setUsername(name);
        user.setPassword(pass);
        User userInfo = userInfoDao.save(user);
        assertNotNull(userInfo.getUserId());
    }

    @Test
    public void findByUsername() throws Exception {
        User user = userInfoDao.findByUsername(name);
        assertNotNull(user);
    }

    @Test
    public void findByUsernameAndPassword() throws Exception {
        User user = userInfoDao.findByUsernameAndPassword(name, pass);
        assertNotNull(user);
    }

    @Test
    public void findByMailAndPassword() throws Exception {
        User user = userInfoDao.findByMailAndPassword(mail, pass);
        assertNotNull(user);
    }

    @Test
    public void updatePassword() throws Exception {
        String newPass = "testNew";
        int count = userInfoDao.updatePasswordByName(name, newPass);
        assertEquals(1, count);
    }

}