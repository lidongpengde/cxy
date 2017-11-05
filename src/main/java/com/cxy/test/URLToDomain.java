package com.cxy.test;

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

    public void parseDomain() throws IOException {
        User user = null;
        if (StringUtils.isEmpty(user.getMobile()))
            System.out.println("tinghaoder");

    }
    @Test
    public  void main() throws IOException {
        List<User> list=null;
        int a=1;
        boolean is=isMobile("18310183096");
        System.out.println(1^a);

    }
    public  boolean isMobile(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$");

        Matcher m = p.matcher(mobiles);

        System.out.println(m.matches()+"---");

        return m.matches();
    }
}
