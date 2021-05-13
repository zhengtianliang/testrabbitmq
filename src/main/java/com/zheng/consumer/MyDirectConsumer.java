package com.zheng.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ZhengTianLiang
 * @date: 2021/5/13  10:29
 * @desc: 消费点对点消息的  消费者
 */

@RestController
public class MyDirectConsumer {

    /**
     * @author: ZhengTianLiang
     * @date: 2021/5/13  10:33
     * @desc: 消费点对点消息的  消费者
     */
    @RabbitHandler
    @RabbitListener(queues = "my.direct.loginfo")
    public void getInfoMsg(String msg){
        System.err.println("进入了info类型的消息"+msg);
    }

}
