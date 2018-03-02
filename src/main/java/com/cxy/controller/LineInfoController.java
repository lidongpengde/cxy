package com.cxy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cxy.common.*;
import com.cxy.entity.*;
import com.cxy.service.ILineInfoService;
import com.cxy.service.IuserService;
import io.searchbox.client.JestResult;
import io.searchbox.core.Index;
import jdk.nashorn.internal.objects.annotations.Property;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
    private static final Log log = LogFactory.getLog(LineInfoController.class);
    @Autowired
    ILineInfoService lineInfoService;
    @Autowired
    IuserService userService;

    /**
     * 保存行程信息
     * @param request
     * @param lineInfo
     * @return
     */
    @RequestMapping(value = "lineInfo",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public JestResult publishInfo(HttpServletRequest request, LineInfo lineInfo){
        MessageResult messageResult = new MessageResult();
        JestResult jestResult = null;
        if(AtomUtils.isEmpty(lineInfo.getLid())) {
            User user=(User)request.getSession().getAttribute("const_user");
            lineInfo.setUserId(user.getId().toString());
            lineInfo.setUserMobile(user.getMobile().toString());
            lineInfo.setUserNickname(user.getNickName());
            lineInfo.setStatus(1);
            Index index = new Index.Builder(lineInfo).index("go366").type("lineinfo").build();
            try {
                 jestResult=new ESUtils().ceateClient().execute(index);
            } catch (IOException e) {
                e.printStackTrace();
            }
            messageResult = lineInfoService.saveLineInfo(lineInfo, request);
        }else{//再次编辑后自动发布
            lineInfo.setStatus(1);
            messageResult = lineInfoService.updateByLineInfo(lineInfo);
        }
        return jestResult;
    }
    @RequestMapping(value = "lineInfos",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String publishInfo(HttpServletRequest request, LineInfo lineInfo,Integer pageIndex,Integer pageSize){
        /*if (StringUtils.isEmpty(lineInfo.getStart())){
           String adcode= getStartAdressIfLineInfoNull(request);
           lineInfo.setStartAdcode(adcode);
        }*/
        JSONObject jsonObject=new JSONObject();
        Pager list=lineInfoService.queryLineInfoList(lineInfo,pageIndex, pageSize,false);
        List<LineInfoAndUserInfo> listAll=null;
        return JSONObject.toJSONString(list);
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
    public String toIndexPage(HttpServletRequest request, HttpServletResponse response){

        return "index";
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

    @RequestMapping("myMenu")
    public String myMenu(HttpServletRequest request, ModelMap modelMap){
        return "usercenterIndex";
    }


}
