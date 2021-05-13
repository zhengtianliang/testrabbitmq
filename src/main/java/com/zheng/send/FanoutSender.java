package com.zheng.send;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author: ZhengTianLiang
 * @date: 2021/5/10  14:41
 * @desc: 发布订阅模式的发送者
 */

@Service
public class FanoutSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * @author: ZhengTianLiang
     * @date: 2021/5/10  14:49
     * @desc: 发布订阅模式的消息
     */
    public void fanoutSender(String msg){
        Map<String,Object> map = new HashMap<>();
        map.put("msg",msg);
        map.put("id", UUID.randomUUID().toString().replaceAll("\\-",""));

        // 发送消息   amqpTemplate.convertAndSend(交换机名，路由键名，消息);
        amqpTemplate.convertAndSend("my.fanout.exchange",null,map);
//        amqpTemplate.convertAndSend("nz.fanout",null,map);
    }
}
