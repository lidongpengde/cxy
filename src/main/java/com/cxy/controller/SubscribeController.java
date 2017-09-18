package com.cxy.controller;

import com.cxy.common.MessageResult;
import com.cxy.entity.LineInfo;
import com.cxy.entity.Subscribe;
import com.cxy.service.ILineInfoService;
import com.cxy.service.IsubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lidongpeng on 2017/9/18.
 */
@RequestMapping("/api")
@Controller
public class SubscribeController {
    @Autowired
    IsubscribeService subscribeService;
    @Autowired
    ILineInfoService lineInfoService;
    @RequestMapping(value = "subscibe",method = RequestMethod.POST)
    public String sebscribeLineinfo(HttpServletRequest request, Subscribe subscribe,ModelMap modelMap){
        MessageResult result= subscribeService.addSubscibe(request,subscribe);
        List<Subscribe> subscribeList=subscribeService.querySubscibeList((LineInfo) result.getBuessObj());
        modelMap.put("lineinfo",result.getBuessObj());
        modelMap.put("subscribeList",subscribeList);
        return "viewSubscribeResult";
    }
    @RequestMapping(value = "toSubscibe/{lid}",method = RequestMethod.GET)
    public String toSubscibe(@PathVariable String lid,ModelMap modelMap){
        modelMap.put("lineInfoId",lid);
        return "subscribe";
    }

    @RequestMapping(value = "mySubscibe/{lid}",method = RequestMethod.GET)
    public String toSubscibe(@PathVariable Integer lid,ModelMap modelMap){
        LineInfo lineInfo=lineInfoService.queryLineInfoById(lid);
        List<Subscribe> subscribeList=subscribeService.querySubscibeList(lineInfo);
        modelMap.put("lineinfo",lineInfoService.queryLineInfoById(lid));
        modelMap.put("subscribeList",subscribeList);
        return "viewSubscribeResult";
    }
}