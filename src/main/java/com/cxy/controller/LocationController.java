/*
package com.cxy.controller;

import com.alibaba.fastjson.JSONObject;
import com.cxy.dao.LocationMapper;
import com.cxy.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

*/
/**
 * Created by lidongpeng on 2017/8/18.
 *//*

@RequestMapping("/v2")
@Controller
public class LocationController {
    @Autowired
    LocationMapper locationMapper;
    @RequestMapping(value = "/HintInfo",produces = "application/json; charset=utf-8")
    @ResponseBody
    public  String getHintInfo(String query,int limit) {
        List<Location> list=locationMapper.selectByName(query);
        return JSONObject.toJSONString(list);
    }
}
*/
