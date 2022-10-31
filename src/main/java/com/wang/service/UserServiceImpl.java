package com.wang.service;

import com.wang.execut.Callers;
import com.wang.execut.Worker;
import com.wang.pojo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * @author wqy
 */
public class UserServiceImpl implements UserService {
    @Override
    public void insert(ArrayList<User> users) {
        int n = users.size();
        ExecutorService executorService = newFixedThreadPool(n);

        CountDownLatch readyLatch = new CountDownLatch(n);
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(n);

        for(int i = 0; i < n; i++) {
            executorService.execute(
                    new Worker(readyLatch,
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

    @Override
    public List<User> findUserById(List<Integer> ids) {
        List<User> users= new ArrayList<>();
        int size = ids.size();
        ExecutorService executorService = newFixedThreadPool(size);
        List<Callable<User>> callables = new ArrayList<>();
        Callers callers = new Callers();
        for (int i = 0; i < size; i++) {
            int temp =i;
            callables.add(()->callers.call(ids.get(temp)));
        }

        List<Future<User>> futures = null;
        try {
            futures = executorService.invokeAll(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        futures.forEach(future->{
            try {
                User user = future.get();
                users.add(user);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
        return users;
    }

}
