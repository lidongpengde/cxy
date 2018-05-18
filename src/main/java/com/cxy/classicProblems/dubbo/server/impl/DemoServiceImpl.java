package com.cxy.classicProblems.dubbo.server.impl;

import com.cxy.classicProblems.dubbo.server.DemoService;

/**
 * Created by lidongpeng on 2018/4/27.
 */
public class DemoServiceImpl implements DemoService {
    public String sayHello(String name) {
        return "Hello " + name;
    }
}

