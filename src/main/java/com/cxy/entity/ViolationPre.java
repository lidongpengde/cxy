package com.cxy.entity;

import java.util.Map;

/**
 * Created by lidp on 2018/11/7.
 */
public class ViolationPre {
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getReturncode() {
        return returncode;
    }

    public void setReturncode(Integer returncode) {
        this.returncode = returncode;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    Integer returncode;
    Map<String,Object> result;
}
