package com.zheng.controller;

import com.zheng.send.DirectSender;
import com.zheng.send.FanoutSender;
import com.zheng.send.MqSender;
import com.zheng.send.TopicSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ZhengTianLiang
 * @date: 2021/5/10  10:30
 * @desc: 消息发送这controller
 */

@RestController
@RequestMapping("/send")
public class MsgSenderController {

    @Autowired
    private MqSender mqSender; // 普通消息，不带交换机的

    @Autowired
    private FanoutSender fanoutSender; // 扇形交换机的消息

    @Autowired
    private DirectSender directSender; // 点对点交换机的消息

    @Autowired
    private TopicSender topicSender;

    /**
     * @author: ZhengTianLiang
     * @date: 2021/5/10  10:31
     * @desc: 发送消息（不带交换机的消息）
     */
    @GetMapping(value = "/msg/sendmsg.do")
    public String send(String msg){
        mqSender.sendMsg(msg);
        return "SUCCESS";
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2021/5/10  14:55
     * @desc: 发送消息（发布订阅模式的交换机）
     */
    @GetMapping(value = "/msg/fanoutSendmsg.do")
    public String fanoutSender(String msg){
        fanoutSender.fanoutSender(msg);
        return "SUCCESS";
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2021/5/11  10:10
     * @desc: 发送消息（点对点模式的交换机）
     */
    @GetMapping(value = "/msg/direct.do")
    public String directSender(String routingKey,String msg){
        directSender.sendDirectMsg(routingKey,msg);
        return "SUCCESS";
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2021/5/13  10:54
     * @desc: 发送消息（发布订阅模式的交换机）
     */
    @GetMapping(value = "/msg/topic.do")
    public String topicSender(String routingKey,String msg){
        topicSender.sendDirectMsg(routingKey,msg);
        return "SUCCESS";
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2022/7/7  23:22
     * @desc: 发送消息（发送方异常怎么处理）
     */
    @GetMapping(value = "/send/error.do")
    public String sendErrorDo(String msg){
        directSender.sendDirectMsg("info",msg);
        if (1==1){
            throw new RuntimeException("消息发送方发生了异常");
        }
        return "SUCCESS";
    }

}
