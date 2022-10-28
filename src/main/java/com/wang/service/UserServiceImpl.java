package com.wang.service;

import com.wang.execut.Worker;
import com.wang.pojo.User;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;


import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 *
 * @author wqy
 */
public class UserServiceImpl implements Service{
    /**
     * 并发插入用户数据
     * @param users
     */
    @Override
    public void insert(ArrayList<User> users){

        int n = users.size();
        ExecutorService executorService = newFixedThreadPool(n);

        CountDownLatch readyLatch = new CountDownLatch(n);
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(n);

        for(int i = 0; i < n; i++) {
            executorService.execute(new Worker(readyLatch,
                    startLatch, endLatch,
                    users.get(i))
                    );
        }
        try {
            readyLatch.await();
            System.out.println(Thread.currentThread().getName()+"发令开始");
            startLatch.countDown();
            endLatch.await();
            System.out.println(Thread.currentThread().getName()+"所有线程运行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
