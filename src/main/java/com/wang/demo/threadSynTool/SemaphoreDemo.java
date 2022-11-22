package com.wang.demo.threadSynTool;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author wqy
 * 10个车抢3个车位
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for(int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到了车位");
                    //停一会车（暂停一会线程）
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally{
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }

    }
}
