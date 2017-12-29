package com.xkupc.crawler.service;

import com.xkupc.crawler.BaseTestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xk
 * @createTime 2017/12/28 0028 上午 9:59
 * @description
 */
public class RabbitMqService extends BaseTestService {
    @Autowired
    SendmqService sendmqService;
    @Autowired
    ReceivemqService receivemqService;

    @Test
    public void testMq() throws InterruptedException{
        String queuenceName = "hello_test";
        receivemqService.receiveMq(queuenceName);
        sendmqService.sendMq(queuenceName, "Hello World!");
    }

    @Test
    public void testPublicMq() throws InterruptedException{
        String exchange = "registerUser";
        receivemqService.receiveMq1(exchange,"");
        receivemqService.receiveMq2(exchange,"");
        sendmqService.sendMq(exchange, "","Hello World!");
    }
}
