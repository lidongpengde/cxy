package com.cxy.service.Impl;

import com.cxy.dao.TCxyUserMapper;
import com.cxy.entity.TCxyUser;
import com.cxy.service.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lidp on 2017/3/19.
 */
@Service
public class UserSerivceImpl implements IuserService{
    @Autowired
    TCxyUserMapper userMapper;
    public String saveUser(TCxyUser user) {
        int size=userMapper.insert(user);
        return "succces";
    }
}