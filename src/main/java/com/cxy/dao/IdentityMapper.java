package com.cxy.dao;


import com.cxy.entity.Identity;
import com.cxy.service.Iidentity;

import java.util.List;
import java.util.Map;

public interface IdentityMapper {
    int deleteByPrimaryKey(Integer identityId);

    int insert(Identity record);

    int insertSelective(Identity record);

    Identity selectByPrimaryKey(Integer identityId);

    int updateByPrimaryKeySelective(Identity record);

    int updateByPrimaryKey(Identity record);
    public List<Iidentity> getIdentityListByPage(Map<String,Integer> map);
    int getIdentityCount(Identity record);
}