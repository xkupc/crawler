package com.xkupc.crawler.util;

import com.sun.deploy.net.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * @author xk
 * @createTime 2017/11/6 0006 下午 5:32
 * @description 发送请求类
 */
public class HttpClientUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    public static String sendPost(String url, Map<String, String> headers, Map<String, String> params) {
        // 创建HttpClient对象
        CloseableHttpClient client = HttpClients.createDefault();
        return null;
    }

    public static String sendGet(String url, Map<String, String> headers, Map<String, String> params) {
        // 创建HttpClient对象
        CloseableHttpClient client = HttpClients.createDefault();
        StringBuilder reqUrl = new StringBuilder(url);
        String result = "";
        /*
         * 设置param参数
         */
        if (params != null && params.size() > 0) {
            reqUrl.append("?");
            for (Map.Entry<String, String> param : params.entrySet()) {
                reqUrl.append(param.getKey() + "=" + param.getValue() + "&");
            }
            url = reqUrl.subSequence(0, reqUrl.length() - 1).toString();
        }
        HttpGet httpGet = new HttpGet(url);
        return rest(httpGet, client);
    }

    /**
     * 发送请求
     *
     * @param httpRequestBase
     * @param client
     * @return
     */
    private static String rest(HttpRequestBase httpRequestBase, CloseableHttpClient client) {
        String result = null;
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpRequestBase);
            /**
             * 请求成功
             */
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (IOException e) {
            logger.error("网络请求出错，请检查原因");
        } finally {
            // 关闭资源
            try {
                if (response != null) {
                    response.close();
                }
                client.close();
            } catch (IOException e) {
                logger.error("网络关闭错误错，请检查原因");
            }
        }
        return result;
    }

    /**
     * 设置头部
     *
     * @param httpRequestBase
     * @return
     */
    private static void setHead(HttpRequestBase httpRequestBase, Map<String, String> headers) {
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                httpRequestBase.addHeader(header.getKey(), header.getValue());
            }
        }
    }
}
