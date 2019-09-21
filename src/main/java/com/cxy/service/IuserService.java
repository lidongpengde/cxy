package com.cxy.service;

import com.cxy.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by lidp on 2017/3/19.
 */
public interface IuserService {
   int saveUser(User user);
   User findUserById(Long userId);
   User findUserByName(String userName);

   User updateUser(User user);
   User findUserByMobile(String login);
   void   saveLoginStatus(HttpServletRequest request,User user,HttpServletResponse response);
   User getUserBykey(String key);
}
