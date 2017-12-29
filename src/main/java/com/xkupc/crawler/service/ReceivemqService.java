package com.xkupc.crawler.service;

/**
 * @author xk
 * @createTime 2017/12/27 0027 下午 8:02
 * @description
 */
public interface ReceivemqService {

    void receiveMq(String queuenceName);

    void receiveMq(String exchange,String queueName,int i);

    void receiveMq1(String exchange,String queueName);

    void receiveMq2(String exchange,String queueName);
}
