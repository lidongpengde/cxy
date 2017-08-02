package com.cxy.service.Impl;

import com.cxy.dao.LineInfoMapper;
import com.cxy.dao.OrderFromMapper;
import com.cxy.dao.UserMapper;
import com.cxy.entity.LineInfo;
import com.cxy.entity.OrderFrom;
import com.cxy.entity.User;
import com.cxy.service.IorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by lidongpeng on 2017/8/2.
 */
@Service
public class OrderServiceImpl implements IorderService{
    @Autowired
    OrderFromMapper orderMapper;
    @Autowired
    LineInfoMapper lineInfoMapper;
    @Autowired
    UserMapper userMapper;
    private final int waitConfirm=0;
    @Override
    public OrderFrom createOrder(int lid,OrderFrom order) {
        order.setCreateTime(new Date());
        order.setOrderStatus(waitConfirm);
        LineInfo lineInfo=lineInfoMapper.selectByPrimaryKey(lid);
        User user=userMapper.selectByPrimaryKey(Long.parseLong(lineInfo.getUserId()));
        if (lineInfo!=null ){
            order.setPublisherId(Integer.parseInt(lineInfo.getUserId()));
            order.setLineInfoStart(lineInfo.getStart());
            order.setLineInfoEnd(lineInfo.getEnd());
            order.setLineInfoPrice(lineInfo.getPrice());
        }
        if (user!=null){
            order.setPublisherName(user.getUserName());
            order.setPublisherMobile(user.getMobile());
        }
        orderMapper.insert(order);
        return order;
    }

    @Override
    public String cancelOrder(String orderId) {
        return null;
    }

    @Override
    public OrderFrom findOrder(String userId) {
        return null;
    }

    @Override
    public List<OrderFrom> findOrderForList(String userId) {
        return null;
    }

    @Override
    public OrderFrom updateOrder(String userId) {
        return null;
    }
}
