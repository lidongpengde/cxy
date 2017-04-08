package com.cxy.dao;


import com.cxy.entity.Message;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MessageMapper {
    int deleteByPrimaryKey(String msgId);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(String msgId);
    List<Message> selectMessageList (String userId);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
    Integer countMessage(String userId);
}