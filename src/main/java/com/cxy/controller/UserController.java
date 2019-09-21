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
import javax.servlet.http.HttpSession;

/**
 * Created by lidp on 2017/3/19.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IuserService userService;
    
    @Autowired
    private UserTools userTools;
    @RequestMapping(value = "/register",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String saveUser(User user,HttpServletRequest request,HttpServletResponse response){
        JSONObject jsonObject=new JSONObject();
        int size=userService.saveUser(user);
        if (size>0){
            userService.saveLoginStatus(request,user,response);
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
    public String userlogin(User user, HttpServletRequest request,String remindMe,HttpServletResponse response){
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
            userService.saveLoginStatus(request,user,response);

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
    @RequestMapping("/{userId}")
    public String tologin(@PathVariable Long userId, HttpServletRequest request){
       User user= userTools.getCurrentUser(request);
       if (user.getId()==1){
           User identifyUser=userService.findUserById(userId);
           identifyUser.setIdentifyStatus(1l);
           userService.updateUser(identifyUser);
       }
        return "identityList";
    }
    @RequestMapping("inner")
    public String getUserinfo(Long userId, HttpServletRequest request, ModelMap modelMap){
        User user= userTools.getCurrentUser(request);
        if (user==null){
            modelMap.put("result","您还没有登录");
            return "result";
        }
        modelMap.put("userInfo",userService.findUserById(user.getId()));
        return "usercenter";
    }

    @RequestMapping(value = "/update",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String updateUserinfo(User user, HttpServletRequest request, ModelMap modelMap){
         User olduser= userTools.getCurrentUser(request);
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
        User olduser= userTools.getCurrentUser(request);
        if (olduser!=null){
            return true;
        }
        return false;
    }
    public  boolean isMobile(String mobiles) {
        String check = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$";

        Pattern p = Pattern.compile(check);

        Matcher m = p.matcher(mobiles);

        System.out.println(m.matches()+"---");

        return m.matches();
    }

    /**
     * 切换角色
     * @param user
     * @param request
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/changeUserType",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String changeUserType(User user, HttpServletRequest request, ModelMap modelMap){
        HttpSession session = request.getSession();
        Integer type =(Integer) session.getAttribute("user_type");
        session.setAttribute("user_type",1^type);
        JSONObject jsonObject=new JSONObject();
        if (user!=null){
            jsonObject.put("isSuccess",true);
            String name;
            if (type==1){
                 name="乘客身份";
            }else{
                name="司机身份";
            }
            jsonObject.put("message","已切换为"+name);
        }
        return JSONObject.toJSONString(jsonObject);
    }
}
