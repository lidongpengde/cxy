package com.cxy.service.Impl;

import com.cxy.common.UserTools;
import com.cxy.dao.UserMapper;
import com.cxy.dao.UserRecordMapper;
import com.cxy.entity.User;
import com.cxy.entity.UserRecord;
import com.cxy.service.IuserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        //新增用户默认状态为未认证
        user.setIdentifyStatus(0L);
        user.setCreateTime(UserTools.getCurrentTime());
        user.setNickName(user.getUserName());
        int size=userMapper.insert(user);
        return size;
    }

    public User findUserById(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public User findUserByName(String userName) {
        User user=userMapper.selectByPrimaryName(userName);
        if (user!=null){



        }
        return userMapper.selectByPrimaryName(userName);
    }
    /**
     * 0未认证1已认证2黑名单
     * @param user
     * @return
     */
    public User updateUser(User user) {
        //user.setIdentifyStatus(1L);
        int size=userMapper.updateByPrimaryKeySelective(user);
        if (size>0){
          user=  userMapper.selectByPrimaryKey(user.getId());
        }
        return user;
    }

    @Override
    public User findUserByMobile(String login) {
        User user= userMapper.selectByMobile(login);
        return user;
    }

    @Override
    public void saveLoginStatus(HttpServletRequest request,User user) {
        HttpSession session=request.getSession();
        session.setAttribute("const_user",user);
        //如果点了一周免登录按钮，直接将session设置为永久
        String remindMe=request.getParameter("remindMe");
        if (StringUtils.isNotBlank(remindMe)){
            session.setMaxInactiveInterval(7*24*60*60);
        }else{
            session.setMaxInactiveInterval(24*60*60);
        }
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
}
