package com.cxy.controller;

import com.alibaba.fastjson.JSONObject;
import com.cxy.common.UserTools;
import com.cxy.entity.User;
import com.cxy.service.Impl.MailService;
import com.cxy.service.IuserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

/**
 * Created by lidongpeng on 2017/12/12.
 */
@RestController
@RequestMapping(value = "email",produces = "application/json; charset=utf-8")
public class EmailController {
    @Autowired
    IuserService userService;
    @RequestMapping("/sendEmail")
    public String sendEmail(String email, HttpServletRequest request){
        MailService mailService= MailService.getInstance();
        JSONObject jsonObject=new JSONObject();
        String emailcode=UserTools.getFourRandom();
        if (StringUtils.isEmpty(email)){
            jsonObject.put("code","400");
            jsonObject.put("message","请输入邮箱地址");
            return JSONObject.toJSONString(jsonObject);
        }
        try {
            request.getSession().setAttribute("emailcode",emailcode);
            mailService.sendMail("任我行顺风车网",email,"验证码为"+emailcode,"10分钟有效");
            jsonObject.put("code","200");
            jsonObject.put("message","邮件验证码已发送成功");
            return JSONObject.toJSONString(jsonObject);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            jsonObject.put("code","400");
            jsonObject.put("message","邮件验证码已发送成功");
            e.printStackTrace();
            return JSONObject.toJSONString(jsonObject);
        }
        return JSONObject.toJSONString(jsonObject);
    }
    @RequestMapping("/bindEmail")
    public String bindEmail(String email, String code,HttpServletRequest request){
        MailService mailService= MailService.getInstance();
        JSONObject jsonObject=new JSONObject();
        String emailcode=UserTools.getFourRandom();
        if (StringUtils.isEmpty(email)){
            jsonObject.put("code","400");
            jsonObject.put("message","请输入邮箱地址");
            return JSONObject.toJSONString(jsonObject);
        }
        if (StringUtils.isEmpty(code)){
            jsonObject.put("code","400");
            jsonObject.put("message","请输入验证码");
            return JSONObject.toJSONString(jsonObject);
        }
        User user=UserTools.getCurrentUser(request);
        if (user==null){
            jsonObject.put("code","403");
            jsonObject.put("message","您还没有登录");
            return JSONObject.toJSONString(jsonObject);
        }
        try {
            String oldcode=(String) request.getSession().getAttribute("emailcode");
            if (!Objects.equals(oldcode, code)){
                jsonObject.put("code","403");
                jsonObject.put("message","验证码错误");
                return JSONObject.toJSONString(jsonObject);
            }
            user.setEmail(email);
            userService.updateUser(user);
            jsonObject.put("code","200");
            jsonObject.put("message","邮件绑定成功");
            return JSONObject.toJSONString(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.toJSONString(jsonObject);
    }
    @RequestMapping("/getPassword")
    public String getPassword(String email, String mobile){
        MailService mailService= MailService.getInstance();
        JSONObject jsonObject=new JSONObject();
        if (StringUtils.isEmpty(email)){
            jsonObject.put("code","400");
            jsonObject.put("message","请输入邮箱地址");
            return JSONObject.toJSONString(jsonObject);
        }
        if (StringUtils.isEmpty(mobile) ||!UserTools.isMobile(mobile)){
            jsonObject.put("code","400");
            jsonObject.put("message","请输入手机");
            return JSONObject.toJSONString(jsonObject);
        }
        User user=userService.findUserByMobile(mobile);
        if (user==null ||StringUtils.isEmpty(user.getEmail())){
            jsonObject.put("code","403");
            jsonObject.put("message","该用户不存在或邮箱未绑定");
            return JSONObject.toJSONString(jsonObject);
        }

        if(!user.getEmail().equals(email)){
            jsonObject.put("code","403");
            jsonObject.put("message","绑定邮箱和您输入邮箱不一致");
            return JSONObject.toJSONString(jsonObject);
        }
        try {
            mailService.sendMail("任我行顺风车网",email,"您的密码是"+user.getPassWord(),"10分钟有效");
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        jsonObject.put("code","200");
        jsonObject.put("message","密码已发送至您邮箱，请查看");
        return JSONObject.toJSONString(jsonObject);
    }
}
