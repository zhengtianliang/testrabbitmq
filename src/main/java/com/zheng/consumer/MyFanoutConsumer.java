package com.zheng.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: ZhengTianLiang
 * @date: 2021/5/11  9:35
 * @desc: 扇形交换机消息的接受者
 */

@RestController
public class MyFanoutConsumer {

    /**
     * @author: ZhengTianLiang
     * @date: 2021/5/11  9:37
     * @desc: 扇形交换机消息的接受者
     */
    @RabbitListener(queues = "my.fanout.queue1")
    @RabbitHandler
    public void getInfoMsg(Map<String,String> msg){
        System.err.println("俺是监听的my.fanout.queue1的消息"+msg);
        for (Map.Entry m : msg.entrySet()){
            System.err.println("info中的方法"+m.getKey()+":"+m.getValue());
        }
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2021/5/11  9:37
     * @desc: 扇形交换机消息的接受者
     */
    @RabbitListener(queues = "my.fanout.queue2")
    @RabbitHandler
    public void getErrorMsg(Map<String,String> msg){
        System.err.println("俺是监听的my.fanout.queue2的消息============"+msg);
        for (Map.Entry m : msg.entrySet()){
            System.err.println("error中的方法"+m.getKey()+":"+m.getValue());
        }
    }

}
