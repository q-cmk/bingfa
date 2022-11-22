package com.wang.demo.threadCreateMode;

/**
 * @author wqy
 * 实现runnable接口
 */
public class RunnableDemo {
    public static void main(String[] args) {
        Thread.currentThread().setName("主线程");
        new Thread(()->{
            System.out.println("我是实现runnable接口的"+Thread.currentThread().getName()+"中的方法");
        },"子线程").start();
        for(int i=0;i<5;i++){
            System.out.println(Thread.currentThread().getName()+i);
        }
    }
}
