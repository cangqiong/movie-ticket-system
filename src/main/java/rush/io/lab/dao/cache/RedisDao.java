package rush.io.lab.dao.cache;

import rush.io.lab.util.GrapTicketStateEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


/**
 * RedisDao类
 *
 * @author cang
 * @create_time 2017-01-03 19:54
 */
@Repository
public class RedisDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private RedisTemplate<String, String> redisDaoTemplate;

    private final String prefer = "prefer:";

    /**
     * 缓存用户已购票信息
     *
     * @param userId
     * @param ticketId
     */
    public void setUserGrapTicketInfo(String userId, long ticketId) {
        // 超时缓存
        long timeout = 60 * 60; // 1小时
        String key = userId + String.valueOf(ticketId);
        // 表示已经购买该电影票
        redisDaoTemplate.opsForValue().set(key, "1", timeout);
    }

    /**
     * 判断用户是否已经购买该电影票
     *
     * @param userId
     * @param ticketId
     * @return
     */
    public boolean getGrapTicketInfo(String userId, long ticketId) {
        String key = userId + String.valueOf(ticketId);
        Object obj = redisDaoTemplate.opsForValue().get(key);
        if (obj == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 设置电影票的剩余票数
     *
     * @param ticketId
     * @return
     */
    public void setTicketCount(long ticketId, int number) {
        // 超时缓存
        long timeout = 60 * 60; // 1小时
        String key = String.valueOf(ticketId);
        // 表示已经购买该电影票
        redisDaoTemplate.opsForValue().set(key, prefer + number, timeout);
    }

    /**
     * 获取该电影票的数目
     *
     * @param ticketId
     * @return
     */
    public int getTicketCount(long ticketId) {
        String key = String.valueOf(ticketId);
        String res = redisDaoTemplate.opsForValue().get(key);
        if (res == null) {
            return -1;
        } else {
            String result = res.replace(prefer, "");
            return Integer.parseInt(result.trim());
        }
    }

    /**
     * 将缓存电影票数目减1
     *
     * @param ticketId
     */
    public void reduceTicketCount(long ticketId) {
        String key = String.valueOf(ticketId);
        String res = redisDaoTemplate.opsForValue().get(key);
        if (res == null) {
            return;
        } else {
            String result = res.replace(prefer, "");
            int count = Integer.parseInt(result.trim());
            setTicketCount(ticketId, count - 1);
        }
    }


    /**
     * 设置用户抢票的状态
     *
     * @param userId
     * @param ticketId
     * @param stateEnum
     */
    public void setGrapTicketStatus(String userId, long ticketId, GrapTicketStateEnum stateEnum) {
        String key = "status:" + userId + String.valueOf(ticketId);
        // 超时缓存
        long timeout = 60 * 60; // 1小时
        // 表示
        redisDaoTemplate.opsForValue().set(key, prefer + stateEnum.getState(), timeout);
    }

    /**
     * 获取用户抢票的状态
     *
     * @param userId
     * @param ticketId
     */
    public int getGrapTicketStatus(String userId, long ticketId) {
        String key = "status:" + userId + String.valueOf(ticketId);
        String res = redisDaoTemplate.opsForValue().get(key);
        if (res == null) {
            return 2;
        } else {
            String result = res.replace(prefer, "");
            return Integer.parseInt(result.trim());
        }
    }
}
