package com.cxy.dao;


import com.cxy.entity.OrderFrom;

public interface OrderFromMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(OrderFrom record);

    int insertSelective(OrderFrom record);

    OrderFrom selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(OrderFrom record);

    int updateByPrimaryKey(OrderFrom record);
}