package com.cxy.test;

/**
 * Created by lidongpeng on 2017/8/11.
 */
public class ExceptionTest {
    public static void main(String[] args) {
        try {
            throw new RuntimeException("test");
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }


}
