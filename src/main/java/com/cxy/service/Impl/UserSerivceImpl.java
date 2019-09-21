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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by lidp on 2017/3/19.
 */
@Service
public class UserSerivceImpl implements IuserService{
    Map<String,User> loginCache = new HashMap<>();
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
    public void saveLoginStatus(HttpServletRequest request,User user,HttpServletResponse response) {
        HttpSession session=request.getSession();
        //session.setAttribute("const_user",user);
        //如果点了一周免登录按钮，直接将session设置为永久
        String remindMe=request.getParameter("remindMe");
        session.setMaxInactiveInterval(30*24*60*60);
        if(StringUtils.isEmpty(remindMe)){
            remindMe="0";
        }
        session.setAttribute("user_type",Integer.parseInt(remindMe));
        loginCache.put("const_user"+user.getId(),user);
        setCookie(response,"const_user","const_user"+user.getId(),30*24*60*60);

    }
    /**
     * 将cookie封装到Map里面
     *
     * @param request
     * @return
     */
    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

    /**
     * 保存Cookies
     *
     * @param response
     *            servlet请求
     * @param value
     *            保存值
     * @author jxf
     */
    public static HttpServletResponse setCookie(HttpServletResponse response, String name, String value, int time) {

        // new一个Cookie对象,键值对为参数
        Cookie cookie = new Cookie(name, value);

        // tomcat下多应用共享
        cookie.setPath("/");
        // 如果cookie的值中含有中文时，需要对cookie进行编码，不然会产生乱码
        try {
            URLEncoder.encode(value, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        cookie.setMaxAge(time);
        // 将Cookie添加到Response中,使之生效
        response.addCookie(cookie); // addCookie后，如果已经存在相同名字的cookie，则最新的覆盖旧的cookie
        return response;
    }
    @Override
    public User getUserBykey(String key){
       return loginCache.get(key);
    }
}
