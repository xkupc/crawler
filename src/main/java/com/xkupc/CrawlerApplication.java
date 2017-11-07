package com.xkupc;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author xk
 * @createTime 2017/11/6 0006 下午 4:23
 * @description
 */
@SpringBootApplication
public class CrawlerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(CrawlerApplication.class).web(true).run(args);
    }
}
