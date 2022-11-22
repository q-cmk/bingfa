package com.wang.demo.threadCreateMode;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 实现callable接口的方式，创建多线程
 *  callable方式开启的线程与主线程异步执行
 *  task.get()是阻塞方法，写在最后可以最大化整体效率
 * @author wqy
 */
public class CallableDemo implements Callable<Integer> {

    public static void main(String[] args) {
        CallableDemo demo = new CallableDemo();
        //执行Callable方式，需要FutureTask 实现，用于接收运算结果
        FutureTask<Integer> task = new FutureTask<Integer>(demo);
        Thread t = new Thread(task);
        t.start();
        for(int i=0;i<5;i++){
            System.out.println(Thread.currentThread().getName()+"线程在执行"+"---"+i);
        }
        try {
            System.out.println("业务处理结果:"+task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Integer call() throws Exception {
        System.out.println("业务处理...");
        Thread.sleep(2000); //毫秒
        return 1;
    }
}
