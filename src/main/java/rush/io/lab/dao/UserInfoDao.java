package rush.io.lab.dao;

import rush.io.lab.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author cang
 * @create_time 2017-01-05 16:59
 */
@Repository
public interface UserInfoDao extends
        JpaSpecificationExecutor<User>,
        JpaRepository<User, String> {

    /**
     * 查询用户名是否存在
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 使用用户名与密码查询用户信息
     *
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username, String password);

    /**
     * 使用邮箱与密码查询用户信息
     *
     * @param username
     * @param password
     * @return
     */
    User findByMailAndPassword(String username, String password);

    /**
     * 重置密码
     *
     * @param userId
     * @param pass
     * @return
     */
    @Modifying
    @Query("update User u set u.password = ?2 where u.userId = ?1")
    int updatePassword(String userId, String pass);

    /**
     * 重置密码
     *
     * @param userName
     * @param pass
     * @return
     */
    @Modifying
    @Query("update User u set u.password = ?2 where u.username = ?1")
    int updatePasswordByName(String userName, String pass);
}
