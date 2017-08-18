package com.cxy.entity;

import java.util.Map;

/**
 * Created by lidongpeng on 2017/8/17.
 */
public class LocationInfo {
    private String _id;
    private String citycode;
    private  String adcode;
    private  String name;
    private Map<String,Double> center;
    private String level;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Double> getCenter() {
        return center;
    }

    public void setCenter(Map<String, Double> center) {
        this.center = center;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }



}
