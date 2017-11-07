package com.xkupc.crawler.service;

import com.xkupc.crawler.BaseTestService;
import com.xkupc.crawler.model.BaseModel;
import com.xkupc.crawler.model.TcVideo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author xk
 * @createTime 2017/11/7 0007 上午 9:56
 * @description
 */
public class VideoService extends BaseTestService {

    @Autowired
    TcVideoService tcVideoService;

    @Test
    public void test() {
        CrawlerFactory crawlerFactory = new CrawlerFactory();
        List<TcVideo> videoList = crawlerFactory.getResult("https://v.qq.com/x/channel/movie?ptag=film.qq.com");
        tcVideoService.addVideoList(videoList);
    }
}
