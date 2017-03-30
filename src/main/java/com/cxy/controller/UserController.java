package com.cxy.controller;

import com.cxy.common.UserTools;
import com.cxy.entity.TCxyUser;
import com.cxy.service.IuserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

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

    /**
     * 退出
     * @param user
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String userlogout(TCxyUser user, HttpServletResponse response, HttpServletRequest request){
        UserTools.removeUserCookie(response,null);
        return "login";
    }
    @RequestMapping("/toregister")
    public String toregister(TCxyUser user, HttpServletResponse response, HttpServletRequest request){
        return "register";
    }
    @RequestMapping("/tologin")
    public String tologin(TCxyUser user, HttpServletResponse response, HttpServletRequest request){
        return "login";
    }

    /**
     * 完善基本信息
     * @param userId
     * @param modelMap
     * @return
     */
    @RequestMapping("/{userId}")
    public String usercenter(@PathVariable String userId,ModelMap modelMap){
        modelMap.put("user",userService.findUserById(userId));
        return "usercenterIndex";
    }
    @RequestMapping("/toUpdatePage/{userId}")
    public String toUpdatePage(@PathVariable String userId,ModelMap modelMap){
        modelMap.put("user",userService.findUserById(userId));
        return "usercenter";
    }

    @RequestMapping("/update")
    public String toregister(TCxyUser user){
        TCxyUser oldUser=userService.findUserById(user.getUserId());
        /*BeanUtils.copyProperties(oldUser,user,TCxyUser.class);*/
        user.setUserName(oldUser.getUserName());
        user.setUserPassword(oldUser.getUserPassword());
        user.setAge(oldUser.getAge());
        user.setJob(oldUser.getJob());
        user.setUserSex(oldUser.getUserSex());
        user.setProvince(oldUser.getProvince());
        user.setCity(oldUser.getCity());
        user.setArea(oldUser.getArea());
        user.setCreateDate(new Date().toString());
        userService.updateUser(user);
        return "redirect:main";
    }
    @RequestMapping("/main")
    public String mainPage(ModelMap modelMap,TCxyUser user, HttpServletRequest request){
        user=UserTools.getCurrentUser(request);
        modelMap.put("userlist",userService.findUserList(user));
        modelMap.put("user",user);
        return "index";
    }
}
