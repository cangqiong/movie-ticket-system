package rush.io.lab.service;

import rush.io.lab.entity.User;

import java.util.Map;

/**
 * 用户服务类
 *
 * @author cang
 * @create_time 2017-01-05 23:07
 */
public interface UserService {

    /**
     * 用户使用用户名登陆
     *
     * @param username
     * @param password
     * @return
     */
    User loginByUsername(String username, String password);

    /**
     * 用户使用邮箱登录
     *
     * @param mail
     * @param password
     * @return
     */
    User loginByMail(String mail, String password);

    /**
     * 用户注册
     *
     * @param mail
     * @param username
     * @param password
     * @return
     */
    Map<String, Object> registerByMail(String mail, String username, String password);

    /**
     * 重置密码
     *
     * @param username
     * @param password
     * @return
     */
    Map<String, Object> resetPasswd(String username, String password);
}
