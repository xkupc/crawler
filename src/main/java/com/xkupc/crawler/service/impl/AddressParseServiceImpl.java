package com.xkupc.crawler.service.impl;

import com.xkupc.crawler.model.Region;
import com.xkupc.crawler.service.ParseService;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Strings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xk
 * @createTime 2017/11/15 0015 下午 7:05
 * @description 地址解析
 */
@Service
public class AddressParseServiceImpl implements ParseService<Region> {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<Region> praseHtml(String result) {
        List<Region> videoList = Lists.newArrayList();
        if (Strings.isNullOrEmpty(result)) {
            return null;
        }
        Document document = Jsoup.parse(result);
        if (null == document) {
            return null;
        }

        Elements provinceElements = document.select("div[id=demo3]").select("p").select("select[name=province]");
        Map<String, String> provinceMap = new HashMap<>(34);
        for (Element element : provinceElements) {
            for (Element childElement : element.children()) {
                provinceMap.put(childElement.attr("value"), childElement.text());
            }
        }
        redisTemplate.opsForHash().putAll("province", provinceMap);
        return videoList;
    }
}
