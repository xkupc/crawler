package com.xkupc.crawler.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Administrator
 * @createTime 2017/12/27 0027 下午 8:11
 * @description
 */
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitMQConf {

    private String host;

    private int port;

    private String userName;

    private String password;

    private String virtualHost;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    public void setVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
