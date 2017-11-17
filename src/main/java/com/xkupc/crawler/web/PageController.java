package com.xkupc.crawler.web;

import com.alibaba.fastjson.JSON;
import com.xkupc.crawler.model.TcVideo;
import com.xkupc.crawler.service.TcVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Set;

/**
 * @author xk
 * @createTime 2017/11/7 0007 下午 7:21
 * @description
 */
@Controller
@RequestMapping("/html")
public class PageController {
    @Autowired
    RedisTemplate<String, Object> template;
    @Autowired
    TcVideoService tcVideoService;

    @RequestMapping(value = "/{htmlName}", method = RequestMethod.GET)
    public String index(@PathVariable("htmlName") String htmlName,
                        @RequestParam(name = "name", required = false, defaultValue = "visitor")
                                String name, Model model) {
        model.addAttribute("name", name);
        return htmlName;
    }

    @RequestMapping(value = "/{prefix}/{htmlName}", method = RequestMethod.GET)
    public String indexPage(@PathVariable("htmlName") String htmlName, @PathVariable("prefix") String prefix,
                            @RequestParam(name = "name", required = false, defaultValue = "visitor")
                                    String name, Model model) {
        model.addAttribute("name", name);
        htmlName = prefix + "/" + htmlName;
        return htmlName;
    }

    @RequestMapping(value = "/{htmlName}/redirect", method = RequestMethod.GET)
    public void redirect(@PathVariable("htmlName") String htmlName,
                         @RequestParam(name = "url") String url,
                         @RequestParam(name = "name", required = false, defaultValue = "visitor")
                                 String name, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        model.addAttribute("name", name);
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.err.println(JSON.toJSONString(cookie));
        }
        response.sendRedirect(url);
    }
}
