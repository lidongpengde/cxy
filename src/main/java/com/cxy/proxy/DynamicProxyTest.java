package com.cxy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by lidongpeng on 2018/3/31.
 */
public  class DynamicProxyTest {
     interface IHello{
         void sayHello();
     }
     static class Hello implements IHello{

         @Override
         public void sayHello() {
             System.out.println("hello");
         }
     }
     static class DynamicProxy implements InvocationHandler{
         Object origibalObj;
         Object bind(Object origibalObj){
             this.origibalObj=origibalObj;
             return Proxy.newProxyInstance(origibalObj.getClass().getClassLoader(),origibalObj.getClass().getInterfaces(),this);
         }
         @Override
         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
             System.out.println("welcome");
             return method.invoke(origibalObj,args);
         }
     }

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        IHello hello=(IHello)new DynamicProxy().bind(new Hello());
        hello.sayHello();


    }

}
