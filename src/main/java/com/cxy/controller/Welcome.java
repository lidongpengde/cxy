package com.cxy.controller;

import com.cxy.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lidongpeng on 2017/6/10.
 */
@Controller
public class Welcome {
    @RequestMapping("/")
    public String hello(HttpServletRequest request, HttpServletResponse response) {
        User user=(User)request.getSession().getAttribute("const_user");
        if (user!=null){
            return "index";
        }
        return "register";
    }
}
