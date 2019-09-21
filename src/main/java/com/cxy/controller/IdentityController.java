package com.cxy.controller;

import com.alibaba.fastjson.JSONObject;
import com.cxy.common.Pager;
import com.cxy.common.UserTools;
import com.cxy.common.WarningEnum;
import com.cxy.entity.Identity;
import com.cxy.entity.User;
import com.cxy.service.Iidentity;
import com.cxy.service.IuserService;
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
    @Autowired
    private IuserService userService;
    @Autowired
    private UserTools userTools;

    @RequestMapping("toIdentify")
    public String toIdentify(){
        return "identify";
    }
    @RequestMapping(value = "Identify",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String addIdentify(HttpServletRequest request, Identity identity, ModelMap modelMap){
        JSONObject jsonObject=new JSONObject();
        User user=userTools.getCurrentUser(request);
        String path=request.getParameter("path");
        identity.setUserId(user.getId());
        if (user.getIdentifyStatus()==3){
            jsonObject.put("isSuccess",false);
            jsonObject.put("message","您已提交认证信息，无需重复提交！");
            jsonObject.put("code",400);
            return JSONObject.toJSONString(jsonObject);
        }
        int size=identityService.saveIdentity(identity,path);
        if (size>0){
            jsonObject.put("message", WarningEnum.identify_wait.getMsg());
            jsonObject.put("code",200);
        }else{
            jsonObject.put("message", WarningEnum.system_error.getMsg());
            jsonObject.put("code",WarningEnum.system_error.getCode());

        }
        user.setAge(UserTools.getAgeByIdCard(identity.getIdCardNumber()));
        //从身份证号获取年龄，更新用户年龄信息
        userService.updateUser(user);
        return jsonObject.toJSONString();
    }
    @RequestMapping(value = "Identifys",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
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
