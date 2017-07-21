package com.cxy.controller;

import com.alibaba.fastjson.JSONObject;
import com.cxy.entity.User;
import com.cxy.service.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lidp on 2017/3/19.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IuserService userService;
    @RequestMapping("/register")
    public String saveUser(User user){
        int size=userService.saveUser(user);
        if (size>0){
            return "index";
        }
        return "login";
    }
    @RequestMapping("/login")
    public String userlogin(User user, HttpServletResponse response, HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
       String oldpassWord= user.getPassWord();
        user=userService.findUserByName(user.getUserName());
        if (user==null){
            jsonObject.put("message","用户不存在");
            jsonObject.put("code",404);
        }
        if (oldpassWord.equals(user.getPassWord())){
            request.getSession().setAttribute("const_user",user);
            jsonObject.put("message","登录成功");
            jsonObject.put("code",200);
        }else{
            jsonObject.put("message","用户名和密码不匹配");
            jsonObject.put("code",500);
        }
        return "index";
    }

}
