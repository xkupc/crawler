package com.xkupc.crawler.service;

import com.xkupc.crawler.model.BaseModel;
import com.xkupc.crawler.model.TcVideo;
import com.xkupc.crawler.service.impl.VideoParseServiceImpl;
import com.xkupc.crawler.util.HttpClientUtils;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xk
 * @createTime 2017/11/7 0007 上午 9:15
 * @description
 */
@Component
public class CrawlerFactory {

    private ParseService parseService;

    public CrawlerFactory() {
        parseService = new VideoParseServiceImpl();
    }

    /**
     * 获取解析结果
     * @param url
     * @return
     */
    public List<TcVideo> getResult(String url) {
        //发送请求
        String result = HttpClientUtils.sendGet(url, null, null);
        if (Strings.isNullOrEmpty(result)) {
            return null;
        }
        return parseService.praseHtml(result);
    }
}
