package com.xkupc.crawler.web;

import com.xkupc.crawler.dto.PageDTO;
import com.xkupc.crawler.dto.TcVideoDTO;
import com.xkupc.crawler.model.TcVideo;
import com.xkupc.crawler.service.TcVideoService;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xk
 * @createTime 2017/11/8 0008 上午 10:00
 * @description ajax请求
 */
@Controller
@RequestMapping("/ajax")
public class WebController {
    @Autowired
    TcVideoService tcVideoService;

    @RequestMapping(value = "/videoList", method = RequestMethod.POST)
    public PageDTO<TcVideo> videoList(@RequestBody TcVideoDTO tcVideoDTO) {
        return tcVideoService.queryVideoList(tcVideoDTO);
    }
    @RequestMapping(value = "/redirect", method = RequestMethod.POST)
    public void redirectVideoUrl(@RequestBody TcVideoDTO tcVideoDTO, HttpServletRequest request, HttpServletResponse response){
        if (Strings.isNullOrEmpty(tcVideoDTO.getVideoUrl())){
            return;
        }
        response.addCookie(new Cookie("o_cookie","1031572219"));
    }
}
