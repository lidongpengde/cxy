package com.cxy.common;

import java.util.Collection;
import java.util.Map;

public class AtomUtils {
    /**
     * 为空判断
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj){
        if(obj==null)
            return true;
        if(obj=="")
            return true;
        if(obj instanceof  String){
            if(((String) obj).length()==0){
                return  true;
            }
        }else if (obj instanceof Collection){
            if(((Collection) obj).size()==0){
                return  true;
            }
        }else if (obj instanceof Map){
            if(((Map) obj).size()==0){
                return  true;
            }
        }
        return  false;
    }
}
