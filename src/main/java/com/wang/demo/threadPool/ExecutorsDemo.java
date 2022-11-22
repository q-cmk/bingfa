package com.wang.demo.threadPool;

import java.util.concurrent.ExecutorService;
import static java.util.concurrent.Executors.*;

/**
 * @author wqy
 * newFixedThreadPool,newSingleThreadExecutor,newCachedThreadPool
 * 三种线程池的比较
 * Fixed：固定的线程数量 常用线程数和最大线程数是同一个数
 * Single：单个线程 常用线程数是1，最大线程数是1
 * Cached: 缓存线程，可多可少 常用线程数是 0，最大线程数是 Integer.MAX_VALUE,
 */
public class ExecutorsDemo {
    public static void main(String[] args) {
        ExecutorService executorService0 = newFixedThreadPool(5);
        ExecutorService executorService1 = newSingleThreadExecutor();
        ExecutorService executorService2 = newFixedThreadPool(5);

        for(int i = 1; i <= 10; i++) {
            final int id =i;
//            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            executorService0.execute(()->{
                System.out.println(Thread.currentThread().getName()+"\t办理业务"+id);
            });
        }
        executorService0.shutdown();
    }
}
