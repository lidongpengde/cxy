package com.cxy.service;

import com.cxy.entity.Message;
import com.cxy.entity.TCxyUser;

import java.util.List;

/**
 * Created by lidongpeng on 2017/4/7.
 */
public interface ImessageService {
    public int sendMessage(Message message);
    public int getMyMessageCount(String userId);
    public List<Message> getMessageList(String userId);
}
