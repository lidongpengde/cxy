package com.cxy.test;

import redis.clients.jedis.Jedis;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class ReflectionHelloWorld {
    public static void main(String[] args){
      /*  Long start=System.currentTimeMillis();
        Jedis jedis = new Jedis("localhost");
        jedis.set("foo1", "bar1");
        Long end =System.currentTimeMillis();
        System.out.println(end-start);
        String value = jedis.get("foo");*/
loop();
    }



    public void print() {
        System.out.println("abc");
    }
    public static   void loop(){
        List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f");
        list.forEach(s -> System.out.println(s));
    }
}