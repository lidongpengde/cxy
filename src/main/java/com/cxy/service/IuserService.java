package com.cxy.service;

import com.cxy.entity.User;

import java.util.List;

/**
 * Created by lidp on 2017/3/19.
 */
public interface IuserService {
    public int saveUser(User user);
    public User findUserById(Long userId);
    public User findUserByName(String userName);

    public int updateUser(User user);
    public User findUserByMobile(String login);
}
