package com.cxy.test;

/**
 * Created by lidongpeng on 2017/8/11.
 * interface也可以定义方法
 */
public interface Fooable {
    default int printInt(){
        int a=3;
        return a;
    }
}
