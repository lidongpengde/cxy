package com.cxy.entity;

import java.util.Date;

public class Violation {
    private Integer id;

    private String cityId;

    private String plateNum;

    private String egnum;

    private String vnum;

    private Long userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public String getEgnum() {
        return egnum;
    }

    public void setEgnum(String egnum) {
        this.egnum = egnum;
    }

    public String getVnum() {
        return vnum;
    }

    public void setVnum(String vnum) {
        this.vnum = vnum;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateyime() {
        return createTime;
    }

    public void setCreateyime(Date createyime) {
        this.createTime = createyime;
    }

    private Date createTime;


}