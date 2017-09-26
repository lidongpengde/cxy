package com.cxy.controller;

import com.alibaba.fastjson.JSONObject;
import com.cxy.common.UserTools;
import com.cxy.entity.User;
import com.cxy.service.IuserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    @RequestMapping(value = "/login",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String userlogin(User user, HttpServletResponse response, HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
       String oldpassWord= user.getPassWord();
        user=userService.findUserByName(user.getUserName());
        if (user==null){
            jsonObject.put("message","用户不存在");
            jsonObject.put("code",404);
            return jsonObject.toJSONString();
    }
        if (oldpassWord.equals(user.getPassWord())){
            request.getSession().setAttribute("const_user",user);

            HttpSession session=request.getSession();
            jsonObject.put("message","登录成功");
            jsonObject.put("code",200);
        }else{
            jsonObject.put("message","用户名和密码不匹配");
            jsonObject.put("code",500);
        }
        return jsonObject.toJSONString();
    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
       request.getSession().removeAttribute("const_user");
        return "register";
    }
    @RequestMapping("/tologin")
    public String tologin(HttpServletRequest request){
        request.getSession().removeAttribute("const_user");
        return "register";
    }
    @RequestMapping("/{userId}")
    public String tologin(@PathVariable Long userId, HttpServletRequest request){
       User user= UserTools.getCurrentUser(request);
       if (user.getId()==1){
           User identifyUser=userService.findUserById(userId);
           userService.updateUser(identifyUser);
       }
        return "identityList";
    }
    @RequestMapping("inner/{userId}")
    public String getUserinfo(@PathVariable Long userId, HttpServletRequest request, ModelMap modelMap){
        User user= UserTools.getCurrentUser(request);
        if (!user.getId().equals(userId)){
            modelMap.put("result","信息油污");
            return "result";
        }
        modelMap.put("userInfo",userService.findUserById(userId));
        return "usercenter";
    }

    @RequestMapping(value = "/update")
    public String updateUserinfo(User user, HttpServletRequest request, ModelMap modelMap){
         User olduser= UserTools.getCurrentUser(request);
        if (!user.getId().equals(olduser.getId())){
            modelMap.put("result","信息有误");
            return "result";
        }
        userService.updateUser(user);
        return "usercenter";
    }

}
