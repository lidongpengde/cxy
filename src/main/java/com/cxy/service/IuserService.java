package com.cxy.service;

import com.cxy.entity.TCxyUser;

/**
 * Created by lidp on 2017/3/19.
 */
public interface IuserService {
    public String saveUser(TCxyUser user);
    public TCxyUser findUserByName(String userName);
}
