package com.cxy.controller;

import com.alibaba.fastjson.JSONObject;
import com.cxy.common.MessageResult;
import com.cxy.common.UserTools;
import com.cxy.common.WarningEnum;
import com.cxy.dao.AdviceBoxMapper;
import com.cxy.entity.AdviceBox;
import com.cxy.entity.LineInfo;
import com.cxy.entity.User;
import com.cxy.service.ILineInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lidongpeng on 2017/6/10.
 *
 * aaa
 */
@Controller
public class Welcome {
    @Autowired
    private AdviceBoxMapper adviceBoxMapper;
    @Autowired
    ILineInfoService lineInfoService;
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
    @RequestMapping(value = "/advice",method = RequestMethod.POST)
    @ResponseBody
    public MessageResult addAdvice(AdviceBox adviceBox) {
        MessageResult result=new MessageResult();
       int size= adviceBoxMapper.insert(adviceBox);
       if (size>0){
           result.setCode(WarningEnum.update_success.getCode());
           result.setSuccess(true);
           return result;
       }
        result.setSuccess(false);
        return result;
    }
    @RequestMapping(value = "/advices",method = RequestMethod.GET)
    public String toAddAdvice(HttpServletRequest request) {

        return "advice/addAdvice";
    }
    @RequestMapping(value = "location/lineInfo",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getLocatedLineInfo(HttpServletRequest request, LineInfo lineInfo){
        /*if (StringUtils.isEmpty(lineInfo.getStart())){
           String adcode= getStartAdressIfLineInfoNull(request);
           lineInfo.setStartAdcode(adcode);
        }*/
        User user=UserTools.getCurrentUser(request);
        JSONObject jsonObject=new JSONObject();
        MessageResult list=lineInfoService.getLineInfoListWithLocation(lineInfo,user);
        return JSONObject.toJSONString(list);
    }
    @RequestMapping("/downloadapk")
    public String download(String filename, HttpServletRequest request,
                           HttpServletResponse response) {
        final String picpath=System.getProperties().getProperty("user.home")+"/upload";
        filename="renwoxing.apk";
        String path = picpath;
        path=path+ File.separator+filename;
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="
                + filename);
        try {
            InputStream inputStream = new FileInputStream(path);

            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }

            // 这里主要关闭。
            os.close();

            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //  返回值要注意，要不然就出现下面这句错误！
        //java+getOutputStream() has already been called for this response
        return null;
    }
    @RequestMapping(value = "saveSinglelineInfo",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    @ResponseBody
    public Integer saveSinglelineInfo(HttpServletRequest request, LineInfo lineInfo){
        MessageResult messageResult = new MessageResult();
        lineInfo.setStatus(1);
        int size = lineInfoService.savelineinfofirsingle(lineInfo);
        return size;
    }
}
