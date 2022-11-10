package com.wang.service;

import com.wang.execut.Callers;
import com.wang.pojo.User;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * @author wqy
 */
public class UserServiceImpl implements UserService {
    @Override
    public void addUsers(ArrayList<User> users) {
        ExecutorService executorService = newFixedThreadPool(users.size());

        Callers callers = new Callers();
        users.forEach(user -> executorService.execute(() -> callers.insert(user)));
        try {
            executorService.awaitTermination(100,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void addUser2(List<User> users){
        ExecutorService executorService = newFixedThreadPool(users.size());
        Callers callers = new Callers();
        //通过stream操作
        List<CompletableFuture<Void>> list = users.stream()
                .map(u -> CompletableFuture.runAsync(() -> callers.insert(u), executorService))
                .collect(Collectors.toList());
        //join阻塞操作直至线程结束
        list.forEach(CompletableFuture::join);
        executorService.shutdown();
    }

    @Override
    public List<User> findUserById(List<Integer> ids) {
        ExecutorService executorService = newFixedThreadPool(ids.size());
        Callers callers = new Callers();

        //通过stream来操作
        Stream<Integer> stream1 = ids.stream();
        //用map方法操作ids中的id,并得到Callable<User>类型的返回值
        Stream<Callable<User>> callableStream = stream1.map(t -> {
            return () -> {
                return callers.call(t);
            };
        });
        //将stream转化为List
        List<Callable<User>> callables = callableStream.collect(Collectors.toList());

        //定义结果列表
        List<Future<User>> futures = null;
        //执行线程体
        try {
            futures = executorService.invokeAll(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //通过stream来操作
        Stream<Future<User>> stream = futures.stream();
        Stream<User> userStream = stream.map(t -> {
            try {
                 return t.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        });
        List<User> users = userStream.collect(Collectors.toList());

        executorService.shutdown();
        return users;
    }

    @Override
    public void delete(List<Integer> ids) {

    }
}
