package com.cxy.test;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by lidongpeng on 2017/8/10.
 *
 */
public class CallableFuture {
    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newSingleThreadExecutor();

        System.out.println("Time At Task Submission : " + new Date());
        Future<String> result = es.submit(new ComplexCalculator());
        // the call to Future.get() blocks until the result is available.So we are in for about a 10 sec wait now
        System.out.println("Result of Complex Calculation is : " + result.get());
        System.out.println("Time At the Point of Printing the Result : " + new Date());
    }
}
