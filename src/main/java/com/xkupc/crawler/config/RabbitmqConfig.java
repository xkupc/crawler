package com.xkupc.crawler.config;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.xkupc.crawler.model.RabbitMQConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author xk
 * @createTime 2017/12/27 0027 下午 8:07
 * @description
 */
@Configuration
@EnableConfigurationProperties(RabbitMQConf.class)
public class RabbitmqConfig {

    @Autowired
    private RabbitMQConf rabbitMQConf;

    @Bean
    public ConnectionFactory createConnectionFactory() {
        System.err.println("远程主机:"+rabbitMQConf.getHost());
        System.err.println("账号:"+rabbitMQConf.getUserName());
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(rabbitMQConf.getHost());
        factory.setVirtualHost(rabbitMQConf.getVirtualHost());
        factory.setPort(rabbitMQConf.getPort());
        factory.setUsername(rabbitMQConf.getUserName());
        factory.setPassword(rabbitMQConf.getPassword());
        return factory;
    }
}
