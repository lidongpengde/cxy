package com.cxy.controller;

import com.alibaba.fastjson.JSONObject;
import com.cxy.common.UserTools;
import com.cxy.service.Impl.MailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by lidongpeng on 2017/12/12.
 */
@RestController
@RequestMapping(value = "email",produces = "application/json; charset=utf-8")
public class EmailController {
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
}
