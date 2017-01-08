package rush.io.lab.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import rush.io.lab.dto.TicketRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 消息发送类
 */
@Component
public class TicketSendMessage {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void sendMessage(String channel, Serializable message) {
        if (message instanceof TicketRequest) {
            redisTemplate.convertAndSend(channel, message);
        } else {
            // 直接抛弃其他信息
        }

    }
}