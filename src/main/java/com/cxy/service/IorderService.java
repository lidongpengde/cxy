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
    public OrderFrom createOrder(OrderFrom order);
    public String cancelOrder( String orderId);
    public OrderFrom findOrder( String userId);
    public List<OrderFrom> findOrderForList( String userId);
    public OrderFrom updateOrder( String userId);
}
