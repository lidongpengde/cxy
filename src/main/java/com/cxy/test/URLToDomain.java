package com.cxy.test;

import com.alibaba.fastjson.JSONObject;
import com.cxy.dao.UserMapper;
import com.cxy.entity.User;

import com.cxy.service.Impl.JestService;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.*;
import java.sql.Connection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by lidongpeng on 2017/9/1.
 */
public class URLToDomain {
    private ThreadLocal<User> userThreadLocal=new ThreadLocal<>();
    /**
     * 根据url获取domain
     * @return
     * @throws IOException
     */

    public int parseDomain(Boolean b){
        return b?1:2;

    }
    @Test
    public  void main() throws IOException {
        for (int i = 0; i <10 ; i++) {
            System.out.println(random01());
        }

    }
    public  boolean isMobile(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$");

        Matcher m = p.matcher(mobiles);
        System.out.println(m.matches()+"---");
        ClassLoader loader=null;
        try {
            Class<?>  userMapper=loader.loadClass("UserMapper");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return m.matches();
    }
    public int random01(){
        Random random=new Random();
        int i=random.nextInt(10);
        int j=random.nextInt(10);
        int result=3;
        while (true){
            if (i==0 && j==1){
                return 1;
            }else if (i==1&&j==0){
                return 0;
            }else {
                 i=random.nextInt(10);
                 j=random.nextInt(10);
                continue;
            }
        }
    }
    @Test
    public void testEs(){
        User user=new User();
        user.setId("5555555555");
        userThreadLocal.set(user);
        user.setId("666666666666666");
    }

}
