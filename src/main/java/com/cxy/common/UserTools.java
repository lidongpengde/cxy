package com.cxy.common;


import com.cxy.entity.TCxyUser;

import javax.servlet.http.HttpServletRequest;
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
}
