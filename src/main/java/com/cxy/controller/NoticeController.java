package com.cxy.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cxy.entity.User;
import com.cxy.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("notice")
public class NoticeController {
    NoticeService noticeService;
    @RequestMapping(value = "getMsgByUser",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getMsgByUser(HttpServletRequest request,String classes){
        User user=(User)request.getSession().getAttribute("const_user");
        List list=null;
        if (user!=null&&user.getId()!=null){
             list =noticeService.getMessByUserAndCla(user.getId(),classes);
        }
        return JSONArray.toJSONString(list);
    }
}
