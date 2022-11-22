package com.wang.demo.threadSynTool;

import java.util.concurrent.CountDownLatch;

/**
 * @author wqy
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        for (int i = 0; i <3;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t"+"离开教室");
                latch.countDown();
            },String.valueOf(i)).start();
        }
        latch.await();
        System.out.println("班长关门走人");
    }
}
