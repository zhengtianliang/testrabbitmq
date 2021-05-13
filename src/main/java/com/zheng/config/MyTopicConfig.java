package com.zheng.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: ZhengTianLiang
 * @date: 2021/5/13  10:40
 * @desc: 主题模式的配置类     (点对点、发布订阅、top主题、head头部。一共有这四种交换器)
 */

@Configuration
public class MyTopicConfig {

    private final String qname1 = "my.topic.studentmsg";
    private final String qname2 = "my.topic.ordermsg";
    private final String qname3 = "my.topic.testinfomsg";

    /**
     * @author: ZhengTianLiang
     * @date: 2021/5/13  10:42
     * @desc: 创建第一个队列
     */
    @Bean
    public Queue createTopicQueue1(){
        return new Queue(qname1);
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2021/5/13  10:42
     * @desc: 创建第二个队列
     */
    @Bean
    public Queue createTopicQueue2(){
        return new Queue(qname2);
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2021/5/13  10:43
     * @desc: 创建第三个队列
     */
    @Bean
    public Queue createTopicQueue3(){
        return new Queue(qname3);
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2021/5/13  10:43
     * @desc: 创建主题交换器对象
     */
    @Bean
    public TopicExchange createTopicExchange(){
        // 交换机名称，是否进行持久化存储，是否自动删除(消息消费者获取消息以后，mq自动将这个消息删除)
        return new TopicExchange("my.topic.exchange",true,true);
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2021/5/13  10:46
     * @desc: 将交换机和队列1绑定起来
     */
    @Bean
    public Binding bindTopicQueue1(){
        // BindingBuilder.bind(队列的对象).to(交换机对象).with(路由键字符串);
        // 其中  *  代表一个单词，是一个单词，不是一个字符，单词是说，两个点之间的内容。星号不包括点、下划线、减号
        return BindingBuilder.bind(createTopicQueue1()).to(createTopicExchange()).with("student.*");
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2021/5/13  10:46
     * @desc: 将交换机和队列2绑定起来
     */
    @Bean
    public Binding bindTopicQueue2(){
        // BindingBuilder.bind(队列的对象).to(交换机对象).with(路由键字符串);
        // 其中  *  代表一个单词，是一个单词，不是一个字符，单词是说，两个点之间的内容。星号不包括点、下划线、减号
        return BindingBuilder.bind(createTopicQueue2()).to(createTopicExchange()).with("order.*");
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2021/5/13  10:47
     * @desc: 将交换机和队列3绑定起来
     */
    @Bean
    public Binding bindTopicQueue3(){
        // BindingBuilder.bind(队列的对象).to(交换机对象).with(路由键字符串);
        // 其中  #  代表0个或多个字符。用 # 的时候，是可以出现点、下划线、减号的
        return BindingBuilder.bind(createTopicQueue3()).to(createTopicExchange()).with("test.#");
    }

}
