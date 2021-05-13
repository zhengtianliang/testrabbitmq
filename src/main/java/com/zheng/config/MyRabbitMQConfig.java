package com.zheng.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: ZhengTianLiang
 * @date: 2021/5/10  9:56
 * @desc: rabbitmq的配置类（不带交换机的方式）
 */

@Configuration
public class MyRabbitMQConfig {

    /**
     * 定义队列名称
     */
    public static String qname1 = "qnz1906_01";

    /**
     * 创建指定的队列
     */
    @Bean
    public Queue createQueue(){
        return new Queue(qname1);
    }


}
