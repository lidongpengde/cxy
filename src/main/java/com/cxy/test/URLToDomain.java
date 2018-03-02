package com.cxy.test;

import com.cxy.dao.UserMapper;
import com.cxy.entity.User;

import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by lidongpeng on 2017/9/1.
 */
public class URLToDomain {
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
        Map<String,String> map=new HashMap<>();
        System.out.println(20+30+40);
        //System.out.println(parseDomain(null));
        isMobile("131");

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
}
