package com.cxy.controller;

import com.alibaba.fastjson.JSONObject;
import com.cxy.common.UserTools;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lidongpeng on 2017/3/24.
 */
@Controller
public class UploadAction {

    private final String picpath=System.getProperties().getProperty("user.home")+"/upload";
    @RequestMapping(value = "/upload.do")
    @ResponseBody
    public String upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
        System.out.println("开始");
        String path = picpath;
        String oldfileName=file.getOriginalFilename();
        String prefix=oldfileName.substring(oldfileName.lastIndexOf("."));
        String fileName= UserTools.getUUID()+prefix;
        //
        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileurl=fileName;
        return fileurl;
    }
    @RequestMapping("/download")
    public String download(String filename, HttpServletRequest request,
                           HttpServletResponse response) {
        String path = picpath;
        path=path+File.separator+filename;
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="
                + path);
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

}