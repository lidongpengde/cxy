package com.cxy.common;


import com.cxy.entity.TCxyUser;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by lidp on 2017/3/19.
 */
public class UserTools {
    public static TCxyUser getCurrentUser(HttpServletRequest request){
        TCxyUser user=(TCxyUser)request.getSession().getAttribute("loginuser");
        return user;
    }
    public static String getUUID(){
        UUID uuid= UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr=str.replace("-", "");
        return uuidStr;
    }
    public static  void removeUserCookie(HttpServletResponse response, String domain){
        Cookie cookie=new Cookie("JSESSIONID", null);
        //cookie.setDomain(domain);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
    public static Date getCurrentTime() {

        return new Date();
    }
}
