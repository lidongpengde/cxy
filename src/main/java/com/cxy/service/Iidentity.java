package com.cxy.service;

import com.cxy.common.Pager;
import com.cxy.entity.Identity;

import java.util.List;

/**
 * Created by lidongpeng on 2017/9/1.
 */
public interface Iidentity {
    public int saveIdentity(Identity identity,String path);
    public Pager findIdentityList(Integer start, Integer pageSize);
}
