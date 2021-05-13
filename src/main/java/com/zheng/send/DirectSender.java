package com.zheng.send;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ZhengTianLiang
 * @date: 2021/5/11  9:49
 * @desc: 点对点交换机的消息发送者
 */

@Service
public class DirectSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * @author: ZhengTianLiang
     * @date: 2021/5/11  9:49
     * @desc: 发送点对点消息
     */
    public void sendDirectMsg(String routingKey, String msg) {
        // 三个参数依次是  交换机名、路由键名、要发送的消息
        rabbitTemplate.convertAndSend("my.direct.exchange",routingKey,msg);
    }


}
