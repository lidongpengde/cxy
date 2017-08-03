package com.cxy.dao;


import com.cxy.entity.OrderFrom;

import java.util.List;

public interface OrderFromMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(OrderFrom record);

    int insertSelective(OrderFrom record);

    OrderFrom selectByPrimaryKey(Integer orderId);

    /**
     * 根据订阅者查
     * @param record
     * @return
     */
    List<OrderFrom> selectByUserId (OrderFrom record);

    /**
     * 根据订阅者或发布者
     * @param orderId
     * @return
     */
    List<OrderFrom> selectOrderListByUserId(Integer userId);

    int updateByPrimaryKeySelective(OrderFrom record);

    int updateByPrimaryKey(OrderFrom record);
}