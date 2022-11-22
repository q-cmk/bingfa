package com.wang.demo.threadCreateMode;

/**
 * @author wqy
 */
public class ThreadDemo extends Thread{
    @Override
    public void run() {
        System.out.println("我是子线程中的方法");
    }

    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.start();
        for (int i = 0; i <5;i++){
            System.out.println(Thread.currentThread().getName()+"正在运行");
        }
    }
}
