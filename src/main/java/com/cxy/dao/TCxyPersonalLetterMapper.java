package com.cxy.dao;


import com.cxy.entity.TCxyPersonalLetter;

import java.util.List;

public interface TCxyPersonalLetterMapper {


    int deleteByPrimaryKey(String letterId);

    int insert(TCxyPersonalLetter record);

    int insertSelective(TCxyPersonalLetter record);


    TCxyPersonalLetter selectByPrimaryKey(String letterId);



    int updateByPrimaryKeySelective(TCxyPersonalLetter record);

    int updateByPrimaryKey(TCxyPersonalLetter record);
}