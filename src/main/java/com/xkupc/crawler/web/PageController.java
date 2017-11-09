package com.xkupc.crawler.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xk
 * @createTime 2017/11/7 0007 下午 7:21
 * @description
 */
@Controller
@RequestMapping("/html")
public class PageController {

    @RequestMapping(value = "/{htmlName}", method = RequestMethod.GET)
    public String index(@PathVariable("htmlName") String htmlName,
                        @RequestParam(name = "name", required = false, defaultValue = "visitor")
                                String name, Model model) {
        model.addAttribute("name", name);
        return htmlName;
    }

    @RequestMapping(value = "/{htmlName}/redirect", method = RequestMethod.GET)
    public void redirect(@PathVariable("htmlName") String htmlName,
                         @RequestParam(name = "url") String url,
                         @RequestParam(name = "name", required = false, defaultValue = "visitor")
                                 String name, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        model.addAttribute("name", name);
        response.sendRedirect(url);
    }
}
