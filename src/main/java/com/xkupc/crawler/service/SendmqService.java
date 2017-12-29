package com.xkupc.crawler.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xk
 * @createTime 2017/12/27 0027 下午 8:01
 * @description
 */
public interface SendmqService {

    void sendMq(String quenceName,String message);

    void sendMq(String exchange ,String queuenceName,String message);
}
