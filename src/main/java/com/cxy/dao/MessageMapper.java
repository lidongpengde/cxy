package com.cxy.dao;


import com.cxy.entity.Message;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageMapper {
    int deleteByPrimaryKey(String msgId);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(String msgId);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
}