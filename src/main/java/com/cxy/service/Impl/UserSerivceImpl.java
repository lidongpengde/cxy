package com.cxy.service.Impl;

import com.cxy.dao.UserMapper;
import com.cxy.entity.User;
import com.cxy.service.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by lidp on 2017/3/19.
 */
@Service
public class UserSerivceImpl implements IuserService{
    @Autowired
    private UserMapper userMapper;
    public int saveUser(User user) {
        int size=userMapper.insert(user);
        return size;
    }

    public User findUserById(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public User findUserByName(String userName) {

        return userMapper.selectByPrimaryName(userName);
    }

    /**
     * 0未认证1已认证2黑名单
     * @param user
     * @return
     */
    public int updateUser(User user) {
        user.setIdentifyStatus(1L);
        return userMapper.updateByPrimaryKey(user);
    }
}
