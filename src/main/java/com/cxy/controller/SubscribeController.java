package com.cxy.controller;

import com.cxy.common.AtomUtils;
import com.cxy.common.MessageResult;
import com.cxy.common.NoticeThread;
import com.cxy.entity.LineInfo;
import com.cxy.entity.Subscribe;
import com.cxy.entity.User;
import com.cxy.service.ILineInfoService;
import com.cxy.service.IsubscribeService;
import com.cxy.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    NoticeService noticeService;
    @RequestMapping(value = "subscibe",method = RequestMethod.POST)
    public String sebscribeLineinfo(HttpServletRequest request, Subscribe subscribe,ModelMap modelMap){
        MessageResult result= subscribeService.addSubscibe(request,subscribe);
        if (!result.isSuccess()){
            modelMap.put("result",result.getMessage());
            return "result";
        }
        List<Subscribe> subscribeList=subscribeService.querySubscibeList((LineInfo) result.getBuessObj());
        modelMap.put("lineinfo",result.getBuessObj());
        modelMap.put("subscribeList",subscribeList);
        //保存至notice中用作消息提醒
        LineInfo lineInfo = lineInfoService.queryLineInfoById(subscribe.getLineinfoId());
        noticeService.saveByNoticeThread(lineInfo);
        return "viewSubscribeResult";
    }
    @RequestMapping(value = "toSubscibe/{lid}",method = RequestMethod.GET)
    public String toSubscibe(HttpServletRequest request,@PathVariable String lid,ModelMap modelMap){
        User user=(User)request.getSession().getAttribute("const_user");
        LineInfo lineInfo = lineInfoService.queryLineInfoById(Integer.parseInt(lid));
        if(lineInfo.getUserId()!=null&&lineInfo.getUserId().equals(user.getId().toString())){
            modelMap.put("result","无法对自己的订单进行预约");
            return "result";
        }
        modelMap.put("lineInfoId",lid);
        return "subscribe";
    }

    @RequestMapping(value = "mySubscibe/{lid}",method = RequestMethod.GET)
    public String toSubscibe(@PathVariable Integer lid,ModelMap modelMap,HttpServletRequest request){
        LineInfo lineInfo=lineInfoService.queryLineInfoById(lid);
        if (lineInfo==null){
            modelMap.put("result","您查找的信息不存在");
            return "result";
        }
        List<Subscribe> subscribeList=subscribeService.querySubscibeList(lineInfo);
        modelMap.put("lineinfo",lineInfoService.queryLineInfoById(lid));
        modelMap.put("subscribeList",subscribeList);
        //查看后设置通知信息已读
        String id = request.getParameter("id");
        if(!AtomUtils.isEmpty(id)){
            noticeService.updById(Long.parseLong(id));
        }
        return "viewSubscribeResult";
    }
}
