package com.zheng.send;

import com.zheng.config.MyRabbitMQConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ZhengTianLiang
 * @date: 2021/5/10  9:57
 * @desc: rabbitmq的消息发送者 最普通的消息，不带消息的交换器
 */

@Service
public class MqSender {

    /*

    @Autowired
    private AmqpTemplate amqpTemplate;

    amqpTemplate.convertAndSend(消息);
    amqpTemplate.convertAndSend(路由键，消息);
    amqpTemplate.convertAndSend(交换机，路由键，消息);

*/

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * @author: ZhengTianLiang
     * @date: 2021/5/10  9:57
     * @desc: rabbitmq的消息发送者
     */
    public void sendMsg(String msg){
        // 路由键，消息
        rabbitTemplate.convertAndSend(MyRabbitMQConfig.qname1,msg);
    }

}
