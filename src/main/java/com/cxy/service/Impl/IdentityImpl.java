package com.cxy.service.Impl;

import com.cxy.common.Pager;
import com.cxy.common.UserTools;
import com.cxy.dao.IdentityMapper;
import com.cxy.entity.Identity;
import com.cxy.service.Iidentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lidongpeng on 2017/9/1.
 */
@Service
public class IdentityImpl implements Iidentity{
    @Autowired
    private IdentityMapper mapper;
    @Override
    public int saveIdentity(Identity identity,String path) {

        identity.setCreateTime(new Date());
        return mapper.insert(identity);
    }

    @Override
    public Pager findIdentityList(Integer pageIndex, Integer pageSize) {
        Pager pager=new Pager();
        Map<String,Integer> map=new HashMap<>();
        map.put("start",pageIndex*pageSize);
        map.put("pageSize",pageSize);
        List<Iidentity> list=mapper.getIdentityListByPage(map);
        Integer total=mapper.getIdentityCount(null);
        pager.setTotal(total.toString());
        pager.setList(list);
        return pager;
    }

}
