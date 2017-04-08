package com.cxy.service.Impl;

import com.cxy.common.UserTools;
import com.cxy.dao.MessageMapper;
import com.cxy.entity.Message;
import com.cxy.entity.TCxyUser;
import com.cxy.service.ImessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lidongpeng on 2017/4/7.
 */
@Service
public class MessageServiceImpl implements ImessageService{
    @Autowired
    MessageMapper messageMapper;
    public int sendMessage(Message message) {
        message.setIsRead(0);
        message.setMsgId(UserTools.getUUID());
        message.setSendTime(UserTools.getCurrentTime());
        int size=messageMapper.insert(message);
        return 0;
    }
    public int getMyMessageCount(String userId) {
        return messageMapper.countMessage(userId);
    }

    public List<Message> getMessageList(String userId) {

        return messageMapper.selectMessageList(userId);
    }
}
