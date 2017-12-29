package com.xkupc.crawler.service.impl;

import com.google.common.base.Strings;
import com.rabbitmq.client.*;
import com.xkupc.crawler.service.ReceivemqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xk
 * @createTime 2017/12/27 0027 下午 8:05
 * @description
 */
@Service
public class ReceivemqServiceImpl implements ReceivemqService {
    @Autowired
    ConnectionFactory connectionFactory;

    @Override
    public void receiveMq(String queuenceName) {
        Connection connection = null;
        try {
            connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(queuenceName, false, false, false, null);
            System.out.println("Waiting for message....");
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    String message = new String(body, "UTF-8");
                    try {
                        System.out.println("receive message:" + message);
                    } finally {
                        //手工确认消息，确认当前的deliveryTag的消息
                        channel.basicAck(envelope.getDeliveryTag(), false);
                    }
                }
            };
            //取消自动ack,等待程序处理结果
            boolean autoAck = false;
            channel.basicConsume(queuenceName, autoAck, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void receiveMq(String exchange, String queueName,int i) {
        Connection connection = null;
        try {
            connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(exchange, BuiltinExchangeType.FANOUT);
            if (Strings.isNullOrEmpty(queueName)) {
                queueName = channel.queueDeclare().getQueue();
            }
            channel.queueBind(queueName, exchange, "");
            System.out.println("Waiting for message....");
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("receive message:" +i+ message);
                }
            };
            channel.basicConsume(queueName, true, consumer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receiveMq1(String exchange, String queueName) {
        receiveMq(exchange,queueName,1);
    }

    @Override
    public void receiveMq2(String exchange, String queueName) {
        receiveMq(exchange,queueName,2);
    }
}
