package com.cxy.controller;

import com.alibaba.fastjson.JSONObject;
import com.cxy.common.MessageResult;
import com.cxy.common.Pager;
import com.cxy.entity.LineInfo;
import com.cxy.entity.LineInfoAndUserInfo;
import com.cxy.entity.User;
import com.cxy.service.ILineInfoService;
import com.cxy.service.IuserService;
import jdk.nashorn.internal.objects.annotations.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "lineInfo",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String publishInfo(HttpServletRequest request, LineInfo lineInfo){
        MessageResult messageResult=lineInfoService.saveLineInfo(lineInfo,request);
        return JSONObject.toJSONString(messageResult);
    }
    @RequestMapping(value = "lineInfos",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String publishInfo( LineInfo lineInfo,Integer pageIndex,Integer pageSize){
        JSONObject jsonObject=new JSONObject();
        Pager list=lineInfoService.queryLineInfoList(lineInfo,pageIndex, pageSize);
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
    @RequestMapping("myPublishLineInfo")
    public String myPublish(HttpServletRequest request, ModelMap modelMap,Integer pageIndex,Integer pageSize){
        User user=(User)request.getSession().getAttribute("const_user");
        LineInfo lineInfo=new LineInfo();
        lineInfo.setUserId(user.getId().toString());
        final Pager mylist=lineInfoService.queryLineInfoList(lineInfo,pageIndex,pageSize);
        modelMap.put("mylist",mylist);
        return "myPublishLineInfo";
    }
    @RequestMapping(value = "lineInfo/{lid}",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String updatePublishInfo( @PathVariable  String lid){
        JSONObject jsonObject=new JSONObject();
        int size=lineInfoService.updateLineInfo(Integer.parseInt(lid));
        if (size>0){
            jsonObject.put("message","发布已取消");
            return JSONObject.toJSONString(jsonObject);
        }
        return JSONObject.toJSONString(jsonObject.put("message","操作异常"));
    }

    /**
     * 获取我的预约消息msg
     * @param request
     * @return
     */
    @RequestMapping(value = "getMsgByUser",produces = "application/text; charset=utf-8")
    @ResponseBody
    public String getMsgByUser(HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("const_user");
        return lineInfoService.getMsgByUser(user);
    }

    /**
     * 获取我的被预约的发布
     * @param request
     * @param modelMap
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping("mySubLineInfo")
    public String mySub(HttpServletRequest request, ModelMap modelMap,Integer pageIndex,Integer pageSize){
        User user=(User)request.getSession().getAttribute("const_user");
        LineInfo lineInfo=new LineInfo();
        lineInfo.setUserId(user.getId().toString());
        final Pager mylist=lineInfoService.querySubLineInfoList(lineInfo,pageIndex,pageSize);
        modelMap.put("mylist",mylist);
        return "myPublishLineInfo";
    }
}
