package com.cxy.service;

import com.cxy.entity.OrderFrom;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by lidongpeng on 2017/8/2.
 */
public interface IorderService {
    public OrderFrom createOrder(int lid,OrderFrom order);
    public String cancelOrder( String orderId);
    public OrderFrom findOrder( int orderId);
    public List<OrderFrom> findOrderForListBySubScribeId( OrderFrom order);
    public List<OrderFrom> findOrderForListByuserId( int userId);
    public OrderFrom updateOrder( OrderFrom record);
}
