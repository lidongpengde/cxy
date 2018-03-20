package com.cxy.service.Impl;

import com.cxy.common.UserTools;
import com.cxy.dao.UserMapper;
import com.cxy.dao.UserRecordMapper;
import com.cxy.entity.User;
import com.cxy.entity.UserRecord;
import com.cxy.service.IJestService;
import com.cxy.service.IuserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by lidp on 2017/3/19.
 */
@Service
public class UserSerivceImpl implements IuserService{
    private ExecutorService executor;
    @Autowired
    IJestService jestService;
    public UserSerivceImpl(){
        executor=Executors.newSingleThreadExecutor();
    }

    public boolean saveUser(User user) {
        boolean stauts=false;
        //新增用户默认状态为未认证
        user.setIdentifyStatus(0L);
        user.setCreateTime(UserTools.getCurrentTime());
        user.setNickName(user.getUserName());
        user.setId(UUID.randomUUID().toString().substring(0,8));


        try {

             stauts=jestService.index(jestService.getJestClient(),"user","user",user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //int size=userMapper.insert(user);
        return stauts;
    }

    public User findUserById(String userId) {
        SearchResponse result=null;
        Gson gson = new GsonBuilder().create();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("id",userId);

        searchSourceBuilder.query(queryBuilder);
        String query = searchSourceBuilder.toString();
        try {
            result=jestService.search(jestService.getJestClient(),"user","user",query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //User user= userMapper.selectByMobile(login);
        User user=null;
        return user;
    }

    public User findUserByName(String userName) {
        SearchResponse result=null;
        Gson gson = new GsonBuilder().create();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("userName",userName);

        searchSourceBuilder.query(queryBuilder);
        String query = searchSourceBuilder.toString();
        long totalHits=0l;
        User user=null;
        try {
            result=jestService.search(jestService.getJestClient(),"user","user",query);
            SearchHits searchHits = result.getHits();
            for (SearchHit hit: searchHits) {
                String res= hit.getSourceAsString();
                user=gson.fromJson(res,User.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    /**
     * 0未认证1已认证2黑名单
     * @param user
     * @return
     */
    public User updateUser(User user) {
        //user.setIdentifyStatus(1L);
        /*int size=userMapper.updateByPrimaryKeySelective(user);
        if (size>0){
          //user=  userMapper.selectByPrimaryKey(user.getId());
        }*/
        return user;
    }

    @Override
    public User findUserByMobile(String login) {
        SearchResponse result=null;
        Gson gson = new GsonBuilder().create();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("mobile",login);

        searchSourceBuilder.query(queryBuilder);
        String query = searchSourceBuilder.toString();
        long totalHits=0l;
        User user=null;
        try {
            result=jestService.search(jestService.getJestClient(),"user","user",query);
            SearchHits searchHits = result.getHits();
            for (SearchHit hit: searchHits) {
               String res= hit.getSourceAsString();
               user=gson.fromJson(res,User.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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
             //   userRecord.setUserId(user.getId());
                userRecord.setUserName(user.getUserName());
                userRecord.setLoginTime(new Date());
               // userRecordMapper.insert(userRecord);
            }
        });
    }
}
