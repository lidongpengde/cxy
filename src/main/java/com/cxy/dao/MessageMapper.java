package com.soft.mapping;

import com.soft.model.Message;

public interface MessageMapper {
    int deleteByPrimaryKey(String msg_id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(String msg_id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
}