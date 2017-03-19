package com.cxy.dao;



import com.cxy.entity.TCxyUser;

import java.util.List;

public interface TCxyUserMapper {


    int deleteByPrimaryKey(String userId);

    int insert(TCxyUser record);

    int insertSelective(TCxyUser record);


    TCxyUser selectByPrimaryKey(String userId);



    int updateByPrimaryKeySelective(TCxyUser record);

    int updateByPrimaryKey(TCxyUser record);
}