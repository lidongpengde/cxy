package com.cxy.classicProblems;

/**
 * Created by lidongpeng on 2018/4/21.
 */
public class StackOwner<E> {
    E[] array;
    int cap;
    int top=-1;
    int size=0;
    public StackOwner(int cap){
        this.cap=cap;
        this.array=(E[])new Object[cap];
    }
    public E peek(){
        if (this.size==0){
            return null;
        }
        E result=array[top];
        return result;
    }
    public E pop(){
        if (this.size==0){
            return null;
        }
        this.size--;
        E result=array[top];
        array[top]=null;
        this.top--;
        return result;
    }

    public boolean push(E object){
        if (object==null){
            return false;
        }
        this.size++;
        array[top+1]=object;
        this.top++;
        return true;
    }
    public boolean ifFull(){
        if (this.size==cap){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        StackOwner<String> stack = new StackOwner<String>(11);
        stack.push("hello");
        stack.push("world");

        System.out.println(stack);


        System.out.println(stack.pop());


        System.out.println(stack.pop());
    }
}
