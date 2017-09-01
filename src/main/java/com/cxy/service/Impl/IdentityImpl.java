package com.cxy.service.Impl;

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
        if (!StringUtils.isEmpty(identity.getIdCard())){
            identity.setIdCard(path);
        }else if (!StringUtils.isEmpty(identity.getLicense())){
            identity.setLicense(path);
        }else if (!StringUtils.isEmpty(identity.getGraduation())){
            identity.setGraduation(path);
        }
        identity.setCreateTime(new Date());
        return mapper.insert(identity);
    }

    @Override
    public List<Iidentity> findIdentityList(String start, String pageSize) {
        Map<String,String> map=new HashMap<>();
        map.put("start",start);
        map.put("pageSize",pageSize);
        return mapper.getIdentityListByPage(map);
    }

}
