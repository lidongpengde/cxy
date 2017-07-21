package com.cxy.controller;

import com.alibaba.fastjson.JSONObject;
import com.cxy.entity.LineInfo;
import com.cxy.entity.User;
import com.cxy.service.ILineInfoService;
import jdk.nashorn.internal.objects.annotations.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lidongpeng on 2017/7/21.
 */
@Controller
@RequestMapping("v1")
public class LineInfoController {
    @Autowired
    ILineInfoService lineInfoService;
    @RequestMapping(value = "lineInfos",method = RequestMethod.POST)
    public JSONObject publishInfo(HttpServletRequest request, LineInfo lineInfo){
        JSONObject jsonObject=new JSONObject();
        User user=(User)request.getSession().getAttribute("const_user");
        lineInfo.setUserId(user.getId().toString());
        int size=lineInfoService.saveLineInfo(lineInfo);
        jsonObject.put("count",size);
        return jsonObject;
    }
    @RequestMapping(value = "lineInfos",method = RequestMethod.GET)
    @ResponseBody
    public String publishInfo( LineInfo lineInfo){
        JSONObject jsonObject=new JSONObject();
        List<LineInfo> list=lineInfoService.queryLineInfoList(lineInfo);
        return JSONObject.toJSONString(list);
    }
    @RequestMapping("toPublishlineInfoPage")
    public String toPublishlineInfoPage(HttpServletRequest request, LineInfo lineInfo){

        return "publishLineInfo";
    }
}
