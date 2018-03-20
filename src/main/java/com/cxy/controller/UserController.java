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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lidp on 2017/3/19.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IuserService userService;
    @RequestMapping(value = "/register",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String saveUser(User user,HttpServletRequest request){
        JSONObject jsonObject=new JSONObject();
        boolean size=userService.saveUser(user);
        if (size){
            userService.saveLoginStatus(request,user);
            jsonObject.put("message","注册成功");
            jsonObject.put("code",200);
            return jsonObject.toJSONString();
        }
        jsonObject.put("message","注册失败");
        jsonObject.put("code",400);
        return jsonObject.toJSONString();
    }
    @RequestMapping("/validateExists/{userName}")
    @ResponseBody
    public String validateExists(@PathVariable String userName){
        User existUser=null;
        if (isMobile(userName)){
            existUser=userService.findUserByMobile(userName);
        }else{
            existUser=userService.findUserByName(userName);
        }
        if (existUser!=null){
            return "true";
        }
        return "false";
    }
    @RequestMapping(value = "/login",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String userlogin(User user, HttpServletRequest request,String remindMe){
        JSONObject jsonObject=new JSONObject();
       String oldpassWord= user.getPassWord();
       String login=user.getUserName();
       if (isMobile(login)){
           user=userService.findUserByMobile(login);
       }else{
           user=userService.findUserByName(user.getUserName());
       }

        if (user==null){
            jsonObject.put("message","用户不存在");
            jsonObject.put("code",404);
            return jsonObject.toJSONString();
    }
        if (oldpassWord.equals(user.getPassWord())){
            userService.saveLoginStatus(request,user);
      /*      if (remindMe=="on"){
                //如果点了一周免登录按钮，直接将session设置为永久
                session.setMaxInactiveInterval(0);
            }*/
            jsonObject.put("refer",request.getSession().getAttribute("refer"));
            jsonObject.put("message","登录成功");
            jsonObject.put("code",200);
        }else{
            jsonObject.put("message","用户名和密码不匹配");
            jsonObject.put("code",500);
        }
        request.getSession().removeAttribute("refer");
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
        String refer=request.getHeader("referer");
        request.getSession().setAttribute("refer",refer);
        return "register";
    }
    @RequestMapping("inner/{userId}")
    public String getUserinfo(@PathVariable String userId, HttpServletRequest request, ModelMap modelMap){
        User user= UserTools.getCurrentUser(request);
        if (user==null){
            modelMap.put("result","您还没有登录");
            return "result";
        }
        if (!user.getId().equals(userId)){
            modelMap.put("result","信息有误！");
            return "result";
        }
        modelMap.put("userInfo",userService.findUserById(userId));
        return "usercenter";
    }

    @RequestMapping(value = "/update",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String updateUserinfo(User user, HttpServletRequest request, ModelMap modelMap){
         User olduser= UserTools.getCurrentUser(request);
         JSONObject jsonObject=new JSONObject();
        if (!user.getId().equals(olduser.getId())){
            jsonObject.put("isSuccess",false);
            jsonObject.put("message","非法访问啊，老铁");
            return JSONObject.toJSONString(jsonObject);
        }
        User oldUser= userService.findUserByMobile(String.valueOf(user.getMobile()));
        if (oldUser!=null){
            jsonObject.put("isSuccess",false);
            jsonObject.put("message","该手机号已被占用");
            return JSONObject.toJSONString(jsonObject);
        }
        oldUser=userService.findUserByName(user.getUserName());
        if (oldUser!=null ){
            jsonObject.put("isSuccess",false);
            jsonObject.put("message","该用户名已被占用");
            return JSONObject.toJSONString(jsonObject);
        }
        user=userService.updateUser(user);
        if (user!=null){
                jsonObject.put("isSuccess",true);
                jsonObject.put("message","修改成功");
        }
        request.getSession().setAttribute("const_user",user);
        return JSONObject.toJSONString(jsonObject);
    }
    @RequestMapping(value = "/checkloginstatus",method = RequestMethod.GET)
    @ResponseBody
    public boolean checkloginstatus(HttpServletRequest request){

        User olduser= UserTools.getCurrentUser(request);
        if (olduser!=null){
            return true;
        }
        return false;
    }
    public  boolean isMobile(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$");

        Matcher m = p.matcher(mobiles);

        System.out.println(m.matches()+"---");

        return m.matches();
    }
}
