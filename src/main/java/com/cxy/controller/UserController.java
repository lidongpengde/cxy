package com.cxy.controller;

import com.cxy.common.UserTools;
import com.cxy.entity.TCxyUser;
import com.cxy.service.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lidp on 2017/3/19.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    IuserService userService;
    @RequestMapping("/save")
    public String saveUser(TCxyUser user){
        user.setUserId(UserTools.getUUID());
        userService.saveUser(user);
        return "login";
    }
    @RequestMapping("/login")
    public String userlogin(TCxyUser user){
        user.setUserId(UserTools.getUUID());
        userService.saveUser(user);
        return "/login";
    }
}
