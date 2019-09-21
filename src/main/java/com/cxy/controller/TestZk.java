/*
package com.cxy.controller;


import com.alibaba.fastjson.JSONObject;
import com.cxy.common.UserTools;
import com.cxy.dao.ViolationMapper;
import com.cxy.entity.User;
import com.cxy.entity.Violation;
import com.cxy.entity.ViolationPre;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.UUID;

*/
/**
 * Created by lidp on 2018/7/23.
 *//*

@Controller
@RequestMapping("violation")
public class TestZk {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestZk.class);
    @Autowired
    ViolationMapper violationMapper;
    @RequestMapping(value = "/query",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String httpPostWithJson(HttpServletRequest request){

        String cityid=request.getParameter("cityid");
        String platenum=request.getParameter("platenum");
        String egnum=request.getParameter("egnum");
        String vnum=request.getParameter("vnum");
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("cityid",cityid);
        jsonObject.put("platenum",platenum);
        // 发动机号
        jsonObject.put("egnum",egnum);
        // 车架号
        jsonObject.put("vnum",vnum);
        jsonObject.put("authcode",request.getParameter("authcode"));
        jsonObject.put("authcodetype","0");
        boolean isSuccess = false;

        HttpPost post = null;
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();

            // 设置超时时间
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);
            String url="https://www.autohome.com.cn/violation/violation/GetViolationTotal";
            //String url="https://www.autohome.com.cn/Violation/Violation/GetViolationList";
            post = new HttpPost(url);
            // 构造消息头
            post.setHeader("Content-type", "application/json; charset=utf-8");
            post.setHeader("Connection", "Close");
            String sessionId = getSessionId();
            post.setHeader("SessionId", sessionId);

            // 构建消息实体
            StringEntity entity = new StringEntity(jsonObject.toString(), Charset.forName("UTF-8"));
            entity.setContentEncoding("UTF-8");
            // 发送Json格式的数据请求
            entity.setContentType("application/json");
            post.setEntity(entity);

            HttpResponse response = httpClient.execute(post);
            JSONObject pre = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
            ViolationPre violationPre=JSONObject.parseObject(pre.getString("Data"), ViolationPre.class);
            if (violationPre.getReturncode() != -54){
                System.out.println("diyibu："+ EntityUtils.toString(response.getEntity(), "utf-8")); // 获取网页内容
                post.releaseConnection();
                post.setURI(new URI("https://www.autohome.com.cn/Violation/Violation/GetViolationList"));
                response = httpClient.execute(post);
            }else{
                String js = violationPre.getResult().get("citys").toString();
                return JSONObject.toJSONString(violationPre);
            }

            HttpEntity entity1=response.getEntity(); // 获取返回实体
            String result = EntityUtils.toString(response.getEntity());
            LOGGER.info(result);
            JSONObject result1 = JSONObject.parseObject(result);
            Violation violation = new Violation();
            violation.setCityId(cityid);
            violation.setEgnum(egnum);
            violation.setPlateNum(platenum);
            violation.setVnum(vnum);
            violation.setCreateyime(new Date());
            User user= UserTools.getCurrentUser(request);
            violation.setUserId(user.getId());
            violationMapper.insert(violation);
            return result1.toString();
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }finally{
            if(post != null){
                try {
                    post.releaseConnection();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    // 构建唯一会话Id
    public static String getSessionId(){
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
    }

}
*/
