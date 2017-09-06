package com.cxy.controller;

import com.alibaba.fastjson.JSONObject;
import com.cxy.common.Pager;
import com.cxy.common.UserTools;
import com.cxy.common.WarningEnum;
import com.cxy.entity.Identity;
import com.cxy.entity.User;
import com.cxy.service.Iidentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lidongpeng on 2017/9/1.
 */
@Controller
@RequestMapping("/api")
public class IdentityController {
    @Autowired
    private Iidentity identityService;

    @RequestMapping("toIdentify")
    public String toIdentify(){
        return "identify";
    }
    @RequestMapping(value = "Identify",method = RequestMethod.POST)
    public String addIdentify(HttpServletRequest request, Identity identity, ModelMap modelMap){
        User user=UserTools.getCurrentUser(request);
        String path=request.getParameter("path");
        identity.setUserId(user.getId());
        identityService.saveIdentity(identity,path);
        modelMap.put("result", WarningEnum.identify_wait.getMsg());
        return "result";
    }
    @RequestMapping(value = "Identifys",method = RequestMethod.GET)
    @ResponseBody
    public String getIdentifyList(Integer pageIndex, Integer pageSize, ModelMap modelMap){
        Pager pager=identityService.findIdentityList(pageIndex,pageSize);

        return JSONObject.toJSONString(pager);
    }
    @RequestMapping(value = "toIdentifys",method = RequestMethod.GET)
    public String toIdentifyList(){


        return "identityList";
    }
}
