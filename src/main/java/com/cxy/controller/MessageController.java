package com.cxy.controller;

import com.alibaba.fastjson.JSONObject;
import com.cxy.common.UserTools;
import com.cxy.entity.Message;
import com.cxy.entity.TCxyUser;
import com.cxy.service.ImessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lidongpeng on 2017/4/7.
 */
@Controller
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private ImessageService messageService;
    @RequestMapping(value = "/addMessage",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addMessage(HttpServletRequest request, Message message){
        JSONObject jsonObject=new JSONObject();
        TCxyUser user=UserTools.getCurrentUser(request);
        message.setSenderId(user.getUserId());
        message.setSenderName(user.getUserName());
        int size=messageService.sendMessage(message);
        if (size==0){
            jsonObject.put("message","发送成功");
            jsonObject.put("code","200");
        }else{
            jsonObject.put("message","发送失败");
            jsonObject.put("code","500");
        }
        return jsonObject;
    }
}
