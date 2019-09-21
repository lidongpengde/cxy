package com.cxy.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cxy.entity.User;
import com.cxy.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("notice")
public class NoticeController {


    @Value("${jdbc.username}")
    String username;
    @Autowired
    NoticeService noticeService;

    @RequestMapping(value = "getMsgByUser",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getMsgByUser(HttpServletRequest request,String classes){
        User user=(User)request.getSession().getAttribute("const_user");
        List list=null;
        if (user!=null){
             list =noticeService.getMessByUserAndCla(user.getId().toString(),classes);
        }
        return JSONArray.toJSONString(list);
    }
}
