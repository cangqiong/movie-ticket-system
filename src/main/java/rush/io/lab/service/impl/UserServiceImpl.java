package rush.io.lab.service.impl;

import rush.io.lab.dao.UserInfoDao;
import rush.io.lab.entity.User;
import rush.io.lab.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户服务实现类
 *
 * @author cang
 * @create_time 2017-01-05 23:16
 */
@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public User loginByUsername(String username, String password) {
        String pass = DigestUtils.md5DigestAsHex(password.getBytes());
        return userInfoDao.findByUsernameAndPassword(username, pass);
    }

    @Override
    public User loginByMail(String mail, String password) {
        String pass = DigestUtils.md5DigestAsHex(password.getBytes());
        return userInfoDao.findByMailAndPassword(mail, pass);
    }

    @Override
    public Map<String, Object> registerByMail(String mail, String username, String password) {
        Map<String, Object> result = new HashMap<String, Object>();
        User user = userInfoDao.findByUsername(username);
        if (user == null) {
            String pass = DigestUtils.md5DigestAsHex(password.getBytes());
            user = new User();
            user.setMail(mail);
            user.setUsername(username);
            user.setPassword(pass);
            user = userInfoDao.save(user);
            if (user.getUserId() != null) {
                result.put("status", "success");
                result.put("user", user);
            } else {
                result.put("status", "failure");
                result.put("error", "注册失败");
            }
        } else {
            result.put("status", "failure");
            result.put("error", "用户名重复");
        }
        return result;
    }

    @Override
    public Map<String, Object> resetPasswd(String username, String password) {
        Map<String, Object> result = new HashMap<String, Object>();
        User user = userInfoDao.findByUsername(username);
        if (user == null) {
            result.put("status", "failure");
            result.put("error", "用户名不存在");
        } else {
            String pass = DigestUtils.md5DigestAsHex(password.getBytes());
            if (user.getPassword().equals(pass)) {
                result.put("status", "failure");
                result.put("error", "两次密码相同");
            } else {
                int updateCount = userInfoDao.updatePassword(user.getUserId(), pass);
                if (updateCount <= 0) {
                    result.put("status", "failure");
                    result.put("error", "重置密码失败");
                } else {
                    result.put("status", "success");
                }
            }
        }
        return result;
    }
}
