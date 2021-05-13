package com.zheng.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: ZhengTianLiang
 * @date: 2021/5/10  16:23
 * @desc: 点对点模式的配置类     (点对点、发布订阅、top主题、head头部。一共有这四种交换器)
 */

@Configuration
public class MyDirectConfig {

    private final String qname1 = "my.direct.loginfo";
    private final String qname2 = "my.direct.logerror";

    /**
     * @author: ZhengTianLiang
     * @date: 2021/5/10  16:24
     * @desc: 创建第一个队列
     */
    @Bean
    public Queue createQueue1(){
        return new Queue(qname1);
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2021/5/10  16:24
     * @desc: 创建第二个队列
     */
    @Bean
    public Queue createQueue2(){
        return new Queue(qname2);
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2021/5/10  16:24
     * @desc: 创建点对点交换器对象
     */
    @Bean
    public DirectExchange createDirectExchange(){
        // 交换机名称，是否进行持久化存储，是否自动删除(消息消费者获取消息以后，mq自动将这个消息删除)
        return new DirectExchange("my.direct.exchange",true,true);
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2021/5/10  16:30
     * @desc: 将交换机和队列1绑定起来
     */
    @Bean
    public Binding bindQueue1(){
        // BindingBuilder.bind(队列的对象).to(交换机对象).with(路由键字符串);
        return BindingBuilder.bind(createQueue1()).to(createDirectExchange()).with("info");
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2021/5/10  16:30
     * @desc: 将交换机和队列2绑定起来
     */
    @Bean
    public Binding bindQueue2(){
        // BindingBuilder.bind(队列的对象).to(交换机对象).with(路由键字符串);
        return BindingBuilder.bind(createQueue2()).to(createDirectExchange()).with("error");
    }

}
