package com.cxy.classicProblems.blockqueue;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lidongpeng on 2018/5/14.
 */
public class BlockqueueTest {

    class producer{
        BlockingQueue<Integer> blockingQueue;
        producer(BlockingQueue<Integer> blockingQueue){
            this.blockingQueue=blockingQueue;
        }
        public boolean putvalue(){
            for (int i = 0; i < 1000; i++) {
               boolean ok= blockingQueue.offer(i);
               return ok;
            }
            return false;
        }
    }
    class Consumer{
        BlockingQueue<Integer> blockingQueue;
        Consumer(BlockingQueue<Integer> blockingQueue){
            this.blockingQueue=blockingQueue;
        }
        public boolean takevalue(){
            while (true){
                try {
                    blockingQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue=new LinkedBlockingDeque<>();
        Executors.newFixedThreadPool(5);
        Executors.newCachedThreadPool();
        ThreadPoolExecutor executorService=new ThreadPoolExecutor(5, 8, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return null;
            }
        },new ThreadPoolExecutor.AbortPolicy());
        executorService.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        new ReentrantLock();
    }
}
