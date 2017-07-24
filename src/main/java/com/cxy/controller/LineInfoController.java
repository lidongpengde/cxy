package com.cxy.controller;

import com.alibaba.fastjson.JSONObject;
import com.cxy.entity.LineInfo;
import com.cxy.entity.LineInfoAndUserInfo;
import com.cxy.entity.User;
import com.cxy.service.ILineInfoService;
import com.cxy.service.IuserService;
import jdk.nashorn.internal.objects.annotations.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by lidongpeng on 2017/7/21.
 */
@Controller
@RequestMapping("v1")
public class LineInfoController {
    @Autowired
    ILineInfoService lineInfoService;
    @Autowired
    IuserService userService;
    @RequestMapping(value = "lineInfo",method = RequestMethod.POST)
    @ResponseBody
    public JSONObject publishInfo(HttpServletRequest request, LineInfo lineInfo){
        JSONObject jsonObject=new JSONObject();
        User user=(User)request.getSession().getAttribute("const_user");
        lineInfo.setUserId(user.getId().toString());
        lineInfo.setStatus(1);
        int size=lineInfoService.saveLineInfo(lineInfo);
        jsonObject.put("count",size);
        return jsonObject;
    }
    @RequestMapping(value = "lineInfos",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String publishInfo( LineInfo lineInfo){
        JSONObject jsonObject=new JSONObject();
        List<LineInfoAndUserInfo> list=lineInfoService.queryLineInfoList(lineInfo);
        List<LineInfoAndUserInfo> listAll=null;
        return JSONObject.toJSONString(list);
    }
    @RequestMapping("toPublishlineInfoPage")
    public String toPublishlineInfoPage(HttpServletRequest request, LineInfo lineInfo){

        return "publishLineInfo";
    }
    @RequestMapping("toIndexPage")
    public String toIndexPage(HttpServletRequest request, HttpServletResponse response){

        return "index";
    }
}
