package com.cxy.classicProblems;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by lidongpeng on 2018/4/25.
 */
public class QueueOwner<E> {
   E[] array;
   int size;
   int head=-1;
   int trail=-1;
   public QueueOwner(int cap){
       array=(E[])new Object[cap];
   }
   public E pop(){
       if (size==0){
           return null;
       }
       E result=array[head];
       head=(head+1)%array.length;
       size--;
       return result;
   }
   public boolean push(E e){
       if (size==array.length){
           return false;
       }
       trail=(trail+1)%array.length;
       array[trail]=e;
       if (head==-1){
           head=trail;
       }
       size++;
       return true;
   }
        public static void main(String[] args) {
            Queue queue=new ArrayBlockingQueue(5);
            QueueOwner<Integer> q = new QueueOwner<Integer>( 5);
            q.push(1);
            q.push(2);
            q.push(3);
            q.push(4);
            q.push(5);
            System.out.println(q.pop());
            q.push(6);
            System.out.println(q.pop());
        }
}
