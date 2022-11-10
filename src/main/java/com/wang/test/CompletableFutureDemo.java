package com.wang.test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author wqy
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //无返回值的情况
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "没有返回值");
        });
        //有返回值的情况
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "有返回值");
//            int age = 10/0;
            return 12;
        });
        final int eXCEVALUE =444;
        /**
         * 正常情况返回t,异常返回定义的异常值。
         */
        System.out.println(integerCompletableFuture.whenComplete((t, u) -> {
            System.out.println("****t:" + t);
            System.out.println("****u:" + u);
        }).exceptionally(f -> {
            System.out.println("***exception:" + f.getMessage());
            return eXCEVALUE;
        }).get());

    }
}
