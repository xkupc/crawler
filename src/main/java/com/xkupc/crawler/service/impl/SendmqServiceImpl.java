package com.xkupc.crawler.service.impl;

import com.rabbitmq.client.*;
import com.xkupc.crawler.service.SendmqService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author xk
 * @createTime 2017/12/27 0027 下午 8:04
 * @description
 */
@Service
public class SendmqServiceImpl implements SendmqService {

    @Autowired
    ConnectionFactory connectionFactory;

    @Override
    public void sendMq(String queuenceName, String message) {
        try {
            //创建连接
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            //声明一个队列,非永久的，非排他的，非自动删除的队列
            channel.queueDeclare(queuenceName, false, false, false, null);
            channel.exchangeDeclare("registerUser", BuiltinExchangeType.FANOUT);
            channel.basicPublish("", queuenceName, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
            System.out.println("send message:" + message);
            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMq(String exchange, String queuenceName, String message) {
        try {
            //创建连接
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(exchange,BuiltinExchangeType.FANOUT);
            channel.basicPublish(exchange, queuenceName, null, message.getBytes("UTF-8"));
            System.out.println("send message:" + message);
            channel.close();
            connection.close();
        } catch (Exception e) {
        }

    }
}
