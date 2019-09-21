package com.test;

import io.reactivex.Flowable;

/**
 * Created by lidp on 2019/2/21.
 */
public class RxExample {
    public static void main(String[] args) {
        Flowable.just("Hello world").subscribe(System.out::println);
        Flowable<Integer> flow = Flowable.range(1, 5)
                .map(v -> v * v)
                .filter(v -> v % 3 == 0)
                ;
    }
}
