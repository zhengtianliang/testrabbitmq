package com.zheng.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: ZhengTianLiang
 * @date: 2021/5/10  14:24
 * @desc: 发布订阅格式的交换器的配置类    (点对点、发布订阅、top主题、head头部。一共有这四种交换器)
 */

@Configuration
public class MyFanoutConfig {

    private final String queueName1 = "my.fanout.queue1";
    private final String queueName2 = "my.fanout.queue2";

    /**
     * @author: ZhengTianLiang
     * @date: 2021/5/10  14:28
     * @desc: 创建队列1
     */
    @Bean(value = "fanoutQueue1")
    public Queue createQueue1(){
        return new Queue(queueName1);
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2021/5/10  14:28
     * @desc: 创建队列2
     */
    @Bean(value = "fanoutQueue2")
    public Queue createQueue2(){
        return new Queue(queueName2);
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2021/5/10  14:29
     * @desc: 创建指定格式(发布订阅)的交换器
     */
    @Bean
    public FanoutExchange createFanoutExchange(){
        return new FanoutExchange("my.fanout.exchange");
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2021/5/10  14:31
     * @desc: 将队列1绑定到交换机上(交换器绑定了多少个队列，就需要绑定多少次)
     *   因为这次是发布订阅格式，所以没有路由键，别的是
     *      BindingBuilder.bind(队列对象).to(交换机对象).with(路由键字符串);
     */
    @Bean(value = "fanoutBinding1")
    public Binding createBinding1(){
        // BindingBuilder.bind(队列的对象).to(交换机的对象);
//        return BindingBuilder.bind(createQueue1()).to(fanoutExchange);
        return BindingBuilder.bind(createQueue1()).to(createFanoutExchange());
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2021/5/10  14:31
     * @desc: 将队列2绑定到交换机上(交换器绑定了多少个队列，就需要绑定多少次)
     *   因为这次是发布订阅格式，所以没有路由键，别的是
     *      BindingBuilder.bind(队列对象).to(交换机对象).with(路由键字符串);
     */
    @Bean(value = "fanoutBinding2")
    public Binding createBinding2(){
        // BindingBuilder.bind(队列的对象).to(交换机的对象);
        return BindingBuilder.bind(createQueue2()).to(createFanoutExchange());
    }

}
