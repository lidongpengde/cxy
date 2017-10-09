package com.cxy.service.Impl;

import com.cxy.common.UserTools;
import com.cxy.dao.UserMapper;
import com.cxy.dao.UserRecordMapper;
import com.cxy.entity.User;
import com.cxy.entity.UserRecord;
import com.cxy.service.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by lidp on 2017/3/19.
 */
@Service
public class UserSerivceImpl implements IuserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRecordMapper userRecordMapper;
    private ExecutorService executor;
    public UserSerivceImpl(){
        executor=Executors.newSingleThreadExecutor();
    }

    public int saveUser(User user) {
        int size=userMapper.insert(user);
        return size;
    }

    public User findUserById(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public User findUserByName(String userName) {
        User user=userMapper.selectByPrimaryName(userName);
        if (user!=null){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    UserRecord userRecord=new UserRecord();
                    userRecord.setUserId(user.getId());
                    userRecord.setUserName(user.getUserName());
                    userRecord.setLoginTime(new Date());
                    userRecordMapper.insert(userRecord);
                }
            });


        }
        return userMapper.selectByPrimaryName(userName);
    }

    /**
     * 0未认证1已认证2黑名单
     * @param user
     * @return
     */
    public int updateUser(User user) {
        user.setIdentifyStatus(1L);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User findUserByMobile(String login) {
        User user= userMapper.selectByMobile(login);
        return user;
    }
}
