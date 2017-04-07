package com.cxy.controller;

import com.cxy.common.UserTools;
import com.cxy.entity.Message;
import com.cxy.entity.TCxyUser;
import com.cxy.service.ImessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String addMessage(HttpServletRequest request, Message message){
        TCxyUser user=UserTools.getCurrentUser(request);
        message.setSenderId(user.getUserId());
        message.setSenderName(user.getUserName());
        messageService.sendMessage(message);
        return null;
    }
}
