package com.cxy.test;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by lidongpeng on 2017/12/7.
 */
public class MultiThread {
    ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private Lock readLock=rwLock.readLock();

    private Lock writelock=rwLock.writeLock();
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
    public  void main123()
    {
        for (int i = 1; i < 10; i++)
        {
            //启动线程进行读操作
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    while (true)
                    {
                        get();
                        put(new Random().nextInt(10000));
                    }
                }

            }).start();

            //启动线程进行写操作
            /*new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    while(true)
                    {
                       put(new Random().nextInt(10000));
                    }
                }
            }).start();*/
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void get()
    {
        rwLock.readLock().lock();//加读锁
        try
        {
            System.out.println(Thread.currentThread().getName() + "be ready to get data");
            Thread.sleep((long) (Math.random() * 1000));

            System.out.println(Thread.currentThread().getName() + "get the data:    " );

        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } finally
        {
            rwLock.readLock().unlock();//释放读锁
        }
    }

    public void put(int data)
    {
        rwLock.writeLock().lock();//加写锁

        try
        {
            System.out.println(Thread.currentThread().getName() + " be ready to write data");

            Thread.sleep((long) (Math.random() * 10000000));

            System.out.println(Thread.currentThread().getName() + " has wrote the data:  "+data);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        } finally
        {
            rwLock.writeLock().unlock();//释放写锁
        }

    }
}
