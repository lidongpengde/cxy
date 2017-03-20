package com.cxy.common;


import java.util.UUID;

/**
 * Created by lidp on 2017/3/19.
 */
public class UserTools {
    /*public static User getCurrentUser(){
        User user=new User();
        return user;
    }*/
    public static String getUUID(){
        UUID uuid= UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr=str.replace("-", "");
        return uuidStr;
    }
}
