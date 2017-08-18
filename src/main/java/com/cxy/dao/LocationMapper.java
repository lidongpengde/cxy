package com.cxy.dao;


import com.cxy.entity.Location;

import java.util.List;

public interface LocationMapper {
    int deleteByPrimaryKey(String id);

    int insert(Location record);

    int insertSelective(Location record);

    Location selectByPrimaryKey(String id);

    List<Location> selectByName(String name);


    int updateByPrimaryKeySelective(Location record);

    int updateByPrimaryKey(Location record);
}