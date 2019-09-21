package com.cxy.service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by lidp on 2019/3/28.
 */
@Component
public class Demo {

    @Autowired
    private RedisTemplate redisTemplate;
    private String redisKey;

    private static final String success="执行成功";

    public String getRedisKey() {
        return redisKey;
    }

    public void setRedisKey(String redisKey) {
        this.redisKey = redisKey;
    }

    public List<String> getRedisList() {
        ListOperations<String, String> ops = redisTemplate.opsForList();
        List<String> result = ops.range(redisKey,0,-1);
        return result;
    }
    public Map<String, Object>  getRedisHash() {
        HashOperations<String, String,Object> ops = redisTemplate.opsForHash();
        Map<String, Object> result = ops.entries(redisKey);
        return result;
    }
    public Object  getRedisString() {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String result = ops.get(redisKey);
        return result;
    }
    public Object  getRedisZSet() {
        ZSetOperations<String, String> ops = redisTemplate.opsForZSet();
        Set<ZSetOperations.TypedTuple<String>> result = ops.rangeWithScores(redisKey,0,-1);
        return JSONObject.toJSONString(result);
    }
    public String deleteRedis() {
        redisTemplate.delete(redisKey);
        return success;
    }
}
