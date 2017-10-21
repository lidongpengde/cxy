package com.cxy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cxy.common.LocationUtil;
import com.cxy.common.MessageResult;
import com.cxy.common.Pager;
import com.cxy.entity.*;
import com.cxy.service.ILineInfoService;
import com.cxy.service.IuserService;
import jdk.nashorn.internal.objects.annotations.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
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
    public String publishInfo(HttpServletRequest request, LineInfo lineInfo,Integer pageIndex,Integer pageSize){
        if (StringUtils.isEmpty(lineInfo.getStart())){
           String address= getStartAdressIfLineInfoNull(request);
           lineInfo.setStart(address);
        }
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
    private String getStartAdressIfLineInfoNull(HttpServletRequest request){
        String ip=request.getRemoteAddr();
        String defaltAddress=null;
        try {
            defaltAddress=LocationUtil.getLocation(ip);
            Address location = JSON.parseObject(defaltAddress, Address.class);
            String[] array=defaltAddress.split(",");
            List list=Arrays.asList(array);
            if (location.getAdcode().replace("[]","")=="") {
                return "";
            }else{
                return location.getCity().replace("[]","");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return  defaltAddress;
    }


}
