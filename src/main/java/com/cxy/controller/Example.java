package com.cxy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lidongpeng on 2017/6/6.
 */
public class Example {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-service.xml");
    // inject the actual template
 /*   @Autowired
    private RedisTemplate<String, String> template;*/

    // inject the template as ListOperations
    // can also inject as Value, Set, ZSet, and HashOperations
    /*@Resource(name="redisTemplate")
    private ListOperations<String, String> listOps;*/
    public static void main(String[] args) {
        new Example().addLink("123","123");
    }
    public   void addLink(String userId, String url) {

        RedisTemplate<String, String>  redisTemplate=(RedisTemplate)context.getBean("redisTemplate");

        ValueOperations<String, String> ops= redisTemplate.opsForValue();
        Long Start=System.currentTimeMillis();
        ops.set("123"," qw");
        Long end=System.currentTimeMillis();
        System.out.println(Start-end);
    }
}