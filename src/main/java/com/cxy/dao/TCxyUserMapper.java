package com.cxy.dao;



import com.cxy.entity.TCxyUser;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TCxyUserMapper {


    int deleteByPrimaryKey(String userId);

    int insert(TCxyUser record);

    int insertSelective(TCxyUser record);


    TCxyUser selectByPrimaryKey(String userId);
    TCxyUser findByUserName(String userName);
    List<TCxyUser> findUserList(TCxyUser user);


    int updateByPrimaryKeySelective(TCxyUser record);

    int updateByPrimaryKey(TCxyUser record);
}