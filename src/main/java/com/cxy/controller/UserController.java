package com.cxy.controller;

import com.cxy.common.UserTools;
import com.cxy.entity.TCxyUser;
import com.cxy.service.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lidp on 2017/3/19.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    IuserService userService;
    @RequestMapping("/savelogin")
    public String saveUser(TCxyUser user){
        user.setUserId(UserTools.getUUID());
        userService.saveUser(user);
        return "login";
    }
    @RequestMapping("/login")
    public String userlogin(TCxyUser user, HttpServletResponse response, HttpServletRequest request){
       String oldpassWord= user.getUserPassword();
        user=userService.findUserByName(user.getUserName());
        if (oldpassWord.equals(user.getUserPassword())){
            Cookie cookie=new Cookie("usercook",user.getUserId());
            response.addCookie(cookie);
            request.getSession().setAttribute("loginuser",user);
        }
        return oldpassWord.equals(user.getUserPassword())?"index":"error";
    }
    @RequestMapping("/tologin")
    public String tologin(TCxyUser user, HttpServletResponse response, HttpServletRequest request){

        return "login";
    }
    @RequestMapping("/main")
    @ResponseBody
    public String mainPage(TCxyUser user, HttpServletResponse response, HttpServletRequest request){

        return "main";
    }
}
