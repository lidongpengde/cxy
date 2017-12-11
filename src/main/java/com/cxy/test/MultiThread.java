package com.cxy.test;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by lidongpeng on 2017/12/7.
 */
public class MultiThread {
    @Test
    public void joinTest(){
    Thread thread=new Thread(new Runnable() {
        int i=0;
        boolean flag=true;
        @Override
        public void run() {
            while (flag){
                System.out.println(i++);
                if (i>100){
                    flag=false;
                }
            }
        }
    });
    thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method main end-----");
    }
    @Test
    public void TreadPoolTest() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(5,10,1000, TimeUnit.MICROSECONDS,new ArrayBlockingQueue<Runnable>(10));
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("hahahahaha1");
            }
        });
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("hahahahaha2");
            }
        });
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("hahahahaha3");
            }
        });
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("hahahahaha4");
            }
        });
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("hahahahaha5");
            }
        });
        Thread.sleep(100);
        System.out.println(1111111111);
    }
}
