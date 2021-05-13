package com.zheng.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: ZhengTianLiang
 * @date: 2021/5/10  11:00
 * @desc: 消息接收者 1
 */

@Component
/*
 这个可以写多个，多个的话：@RabbitListener(queues = {"队列1","队列2"})
 不过不建议这么做，因为队列里面存的数据格式可能会不一样，而一旦不一样，则需要判断一下数据的格式
 比如说队列1存的是string类型，队列2存的是map类型，那么就需要在下面的 @RabbitHandler 中校验数据格式了
 */
@RabbitListener(queues = "qnz1906_01")
public class MyConsumer01 {

    /**
     * @author: ZhengTianLiang
     * @date: 2021/5/10  11:04
     * @desc: 消息接收者1消费消息
     * todo: 注意这个@RabbitHandler是有条件的， 1、无返回值 2、参数固定(不能说这个是string类型参数下次是int类型参数)
     */
    @RabbitHandler  // 对应处理消息的方法
    public void handlerMsg(String msg){
        System.err.println("消费者1消费的消息："+msg);
    }
}
