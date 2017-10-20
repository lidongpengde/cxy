package com.cxy.entity;

public class Notice {
    private Integer id;
    private String userid;
    private String businessid;
    private Integer isread;
    private String classes;
    private String messages;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setBusinessid(String businessid) {
        this.businessid = businessid;
    }

    public void setIsread(Integer isread) {
        this.isread = isread;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public Integer getId() {
        return id;
    }

    public String getUserid() {
        return userid;
    }

    public String getBusinessid() {
        return businessid;
    }

    public Integer getIsread() {
        return isread;
    }

    public String getClasses() {
        return classes;
    }

    public String getMessages() {
        return messages;
    }
}
