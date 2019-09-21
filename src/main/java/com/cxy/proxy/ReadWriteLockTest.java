package com.cxy.proxy;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by lidongpeng on 2018/4/16.
 */
public class ReadWriteLockTest {
    ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    Lock readlock=readWriteLock.readLock();
    Lock writelock=readWriteLock.writeLock();
    private int count=0;
    public void writetest(){
        writelock.lock();
        System.out.println("写锁操作数之前");
        count++;
        writelock.unlock();
        System.out.println("写锁操作数之后");
    }
    public void readtest(){
        readlock.lock();
        System.out.println("读锁操作数之前");
        System.out.println(count);
        readlock.unlock();
        System.out.println("读锁操作数之后");
    }
    @Test
    public void test(){
        for (int i = 0; i <3 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    writetest();
                }
            }).start();
        }
        for (int i = 0; i <3 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    readtest();
                }
            }).start();
        }
    }
}