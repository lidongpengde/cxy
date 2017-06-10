package com.cxy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lidongpeng on 2017/6/10.
 */
@Controller
public class Welcome {
    @RequestMapping("/")
    public String hello() {
        return "redirect:user/main";
    }
}
