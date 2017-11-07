package com.xkupc.crawler.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Administrator
 * @createTime 2017/11/7 0007 下午 7:21
 * @description
 */
@Controller
@RequestMapping("/html")
public class PageController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(@RequestParam(name = "name", required = false, defaultValue = "visitor")
                                String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }
}
