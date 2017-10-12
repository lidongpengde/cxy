package com.cxy.common;


import com.cxy.entity.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by lidp on 2017/3/19.
 */
public class UserTools {
    public static User getCurrentUser(HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("const_user");
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
    public static String getCurrentTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    public static String getCurrentDate() {
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date date=cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }
    public static  void addUserCookie(HttpServletResponse response, String domain){
        Cookie cookie=new Cookie("token", null);
        //cookie.setDomain(domain);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
