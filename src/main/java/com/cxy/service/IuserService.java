package com.cxy.service;

import com.cxy.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lidp on 2017/3/19.
 */
public interface IuserService {
    public int saveUser(User user);
    public User findUserById(Long userId);
    public User findUserByName(String userName);

    public User updateUser(User user);
    public User findUserByMobile(String login);
    public void   saveLoginStatus(HttpServletRequest request,User user);
}
