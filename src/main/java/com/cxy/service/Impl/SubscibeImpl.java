package com.cxy.service.Impl;

import com.cxy.common.MessageResult;
import com.cxy.common.UserTools;
import com.cxy.dao.LineInfoMapper;
import com.cxy.dao.SubscribeMapper;
import com.cxy.entity.LineInfo;
import com.cxy.entity.Subscribe;
import com.cxy.entity.User;
import com.cxy.service.IsubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by lidongpeng on 2017/9/18.
 */
@Service
public class SubscibeImpl implements IsubscribeService{
    @Autowired
    SubscribeMapper subscribeMapper;
    @Autowired
    LineInfoMapper lineInfoMapper;
    @Autowired
    private UserTools userTools;
    @Transactional
    @Override
    public MessageResult addSubscibe(HttpServletRequest request,Subscribe subscribe) {
        MessageResult messageResult=new MessageResult();
        if (subscribe==null){
            messageResult.setSuccess(false);
            return messageResult;
        }
        User user=userTools.getCurrentUser(request);
        subscribe.setPersonId(user.getId());
        subscribe.setPersonName(user.getNickName());
        subscribe.setCreateTime(UserTools.getCurrentTime());
           LineInfo lineInfo= lineInfoMapper.selectByPrimaryKey(subscribe.getLineinfoId());
           int leftSeat=lineInfo.getPersonCount()-subscribe.getPersonCount();
           if (leftSeat<0){
               messageResult.setSuccess(false);
               messageResult.setMessage("座位都被小伙伴占满了！");
               return messageResult;
           }
            int size=subscribeMapper.insertSelective(subscribe);
           lineInfo.setPersonCount(leftSeat);
           lineInfoMapper.updateByPrimaryKey(lineInfo);
            messageResult.setSuccess(true);
            messageResult.setBuessObj(lineInfo);
        return messageResult;
    }

    @Override
    public List<Subscribe> querySubscibeList(LineInfo lineInfo) {

        return subscribeMapper.selectByLineinfoId(lineInfo.getLid());
    }
}
