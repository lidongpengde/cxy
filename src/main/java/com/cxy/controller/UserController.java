package com.cxy.controller;

import com.cxy.common.UserTools;
import com.cxy.entity.TCxyUser;
import com.cxy.service.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        if (user==null)
            return "redirect:tologin";
        if (oldpassWord.equals(user.getUserPassword())){
            Cookie cookie=new Cookie("usercook",user.getUserId());
            response.addCookie(cookie);
            request.getSession().setAttribute("loginuser",user);
        }
        return oldpassWord.equals(user.getUserPassword())?"redirect:main":"error";
    }
    @RequestMapping("/tologin")
    public String tologin(TCxyUser user, HttpServletResponse response, HttpServletRequest request){

        return "login";
    }
    @RequestMapping("/main")
    public String mainPage(ModelMap modelMap,TCxyUser user, HttpServletRequest request){
        user=UserTools.getCurrentUser(request);
        modelMap.put("userlist",userService.findUserList(user));
        modelMap.put("user",user);
        return "index";
    }
}
