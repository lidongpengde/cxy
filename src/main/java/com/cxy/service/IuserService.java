package com.cxy.service;

import com.cxy.entity.TCxyUser;

import java.util.List;

/**
 * Created by lidp on 2017/3/19.
 */
public interface IuserService {
    public String saveUser(TCxyUser user);
    public TCxyUser findUserById(String userId);
    public TCxyUser findUserByName(String userName);
    public List<TCxyUser> findUserList(TCxyUser user);
    public int updateUser(TCxyUser user);
}
