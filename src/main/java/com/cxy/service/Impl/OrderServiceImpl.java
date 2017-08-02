package com.cxy.service.Impl;

import com.cxy.dao.LineInfoMapper;
import com.cxy.dao.OrderFromMapper;
import com.cxy.entity.OrderFrom;
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
    private final int waitConfirm=0;
    @Override
    public OrderFrom createOrder(OrderFrom order) {
        order.setCreateTime(new Date());
        order.setOrderStatus(waitConfirm);
        String publisherId=lineInfoMapper.selectByPrimaryKey(order.getLineInfoId()).getUserId();
        order.setPublisherId(Integer.parseInt(publisherId));
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
