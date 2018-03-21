package com.cxy.controller;

import com.alibaba.fastjson.JSONObject;
import com.cxy.common.*;
import com.cxy.entity.*;
import com.cxy.service.IJestService;
import com.cxy.service.ILineInfoService;
import com.cxy.service.IuserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.naming.directory.SearchResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by lidongpeng on 2017/7/21.
 */
@Controller
@RequestMapping("v1")
public class LineInfoController {
    private static final Log log = LogFactory.getLog(LineInfoController.class);
    @Autowired
    ILineInfoService lineInfoService;
    @Autowired
    IuserService userService;

    @Autowired
    IJestService jestService;
    /**
     * 保存行程信息
     * @param request
     * @param lineInfo
     * @return
     */
    @RequestMapping(value = "lineInfo",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String publishInfo(HttpServletRequest request, LineInfo lineInfo){
        MessageResult messageResult = new MessageResult();
        SearchResult jestResult = null;
        Gson gson = new GsonBuilder().create();
        if(AtomUtils.isEmpty(lineInfo.getLid())) {
            User user=(User)request.getSession().getAttribute("const_user");
            lineInfo.setLid(UUID.randomUUID().toString().substring(0,8));
            lineInfo.setUserId(user.getId().toString());
            lineInfo.setUserMobile(user.getMobile().toString());
            lineInfo.setUserNickname(user.getNickName());
            lineInfo.setStatus(1);
            try {
                List<Object> objs = new ArrayList<Object>();
                objs.add(lineInfo);
                messageResult=lineInfoService.saveLineInfo(lineInfo,request);
                if (messageResult.isSuccess()){
                        messageResult.setCode(WarningEnum.update_success.getCode());
                        messageResult.setMessage(WarningEnum.update_success.getMsg());
                        messageResult.setSuccess(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
        }
        return gson.toJson(messageResult);
    }

    /**
     * 首页展示
     * @param request
     * @param lineInfo
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "lineInfos",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String publishInfo(HttpServletRequest request, LineInfo lineInfo,Integer pageIndex,Integer pageSize){
        SearchResponse result=null;
        JSONObject jsonObject=new JSONObject();
        Gson gson = new GsonBuilder().create();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders
                .rangeQuery("startTime").gt(new Date());

        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.size(100);
        searchSourceBuilder.from(0);
        String query = searchSourceBuilder.toString();
        try {
            result=jestService.search(jestService.getJestClient(),"lineinfo","lineinfo",query);
        } catch (Exception e) {
            e.printStackTrace();
        }
       // Pager list=lineInfoService.queryLineInfoList(lineInfo,pageIndex, pageSize,false);
       // List<LineInfoAndUserInfo> listAll=null;
        return null;
    }
    @RequestMapping("toPublishlineInfoPage")
    public String toPublishlineInfoPage(HttpServletRequest request,ModelMap modelMap, LineInfo lineInfo ){
        String lid = request.getParameter("lid");
        LineInfo alterLine = new LineInfo();
        if(!AtomUtils.isEmpty(lid)){
             alterLine =lineInfoService.queryLineInfoById(Integer.parseInt(lid));
        }
        modelMap.put("alterLine",alterLine);
        return "publishLineInfo";
    }
    @RequestMapping("toIndexPage")
    public String toIndexPage(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap){
        SearchResponse result=null;
        Gson gson = new GsonBuilder().create();
        LineInfo lineInfo=new LineInfo();
        lineInfo.setType(1);
        try {
            List<LineInfo> list =lineInfoService.querySubLineInfoList(lineInfo,0,10);
            modelMap.put("result",list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }
    @RequestMapping("searchMessenger")
    public String searchMessenger(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap){
        LineInfo lineInfo=new LineInfo();
        lineInfo.setType(0);
        try {
            List<LineInfo> list =lineInfoService.querySubLineInfoList(lineInfo,0,10);
            modelMap.put("result",list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "searchMessenger";
    }
    @RequestMapping("myPublishLineInfo")
    public String myPublish(HttpServletRequest request, ModelMap modelMap,Integer pageIndex,Integer pageSize){
        User user=(User)request.getSession().getAttribute("const_user");
        LineInfo lineInfo=new LineInfo();
        lineInfo.setUserId(user.getId().toString());
        final Pager mylist=lineInfoService.queryMyLineInfoList(lineInfo,pageIndex,pageSize);
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
/*    private String getStartAdressIfLineInfoNull(HttpServletRequest request){
        String clientIp = IpAddress.getIpAddrForManyIps(request);
        log.debug("clientIp="+clientIp);
        System.out.println("clientIp="+clientIp);
        String defaltAddress=null;
        try {
            defaltAddress=LocationUtil.getLocation(clientIp);
            Address location = JSON.parseObject(defaltAddress, Address.class);
            String[] array=defaltAddress.split(",");
            List list=Arrays.asList(array);
            log.debug("clientIp="+clientIp);
            if (location.getAdcode().replace("[]","")=="") {
                return "";
            }else{
                return location.getAdcode().replace("[]","");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return  defaltAddress;
    }*/


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


    @RequestMapping("myMenu")
    public String myMenu(HttpServletRequest request, ModelMap modelMap){
        return "usercenterIndex";
    }


}
