package com.cxy.controller;

import com.alibaba.fastjson.JSONObject;
import com.cxy.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lidongpeng on 2017/6/10.
 *
 * aaa
 */
@Controller
public class Welcome {
    @RequestMapping("/")
    public String hello(HttpServletRequest request, HttpServletResponse response) {
        User user=(User)request.getSession().getAttribute("const_user");
        if (user!=null)
            return "index";
        return "register";
    }
    @RequestMapping("/jsonpTest")
    @ResponseBody
    public String jsonpTest(HttpServletRequest request, HttpServletResponse response) {
        Map<String,String> map=new HashMap<>();
        map.put("username","jack");
        map.put("age","21");
        String callBack="success_jsonpCallback("+ JSONObject.toJSONString(map)+")";

        return callBack;
    }
    @RequestMapping("/httpclienttest")
    @ResponseBody
    public String httpclienttest(String key,Map<String,String> map) {

        return key+"oo";
    }
}
