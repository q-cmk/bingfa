package com.wang.demo.threadSynTool;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * 测试Semaphore的使用
 *     将Semaphore作为多并发操作数据库时的流量控制工具
 * @author wqy
 */
public class SemaphoreTest {
    /**
     * 根据线程数量创建线程池，有10个线程
     */
    private static final int THREAD_COUNT =10;
    private static ExecutorService executorService =  newFixedThreadPool(THREAD_COUNT);
    /**
     * 指定信号量的数目，为3，即表示数据有三个连接
     */
    private static Semaphore semaphore = new Semaphore(3);
    public static void main(String[] args) {
        for (int i = 0; i <THREAD_COUNT; i++){
            final int id = i;
            executorService.execute(()->{
                System.out.println(Thread.currentThread().getName()+",id:"+id+"is reading data from remote host");

                try {
                    Thread.sleep(100);
                    //通过acquire方法获取数据连接,如果成功就存储到数据库中
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+",id:"+id+"is saving data...");
                    //模拟存储数据耗时
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally{
                    System.out.println("信号量释放");
                    //最终释放信号量
                    semaphore.release();

                }
            });
        }
    }
}
