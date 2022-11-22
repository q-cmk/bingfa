package com.wang.demo.synchronizedDemo;

import java.util.concurrent.TimeUnit;

/**
 * @author wqy
 *  用于测试synchronized
 *  用法 修饰代码块 synchronized(对象.class)
 *  输出结果为：线程实现了同步
 */
public class SyncDemo02 implements Runnable{
    public static void main(String[] args) {

        SyncDemo02 syncDemo02 = new SyncDemo02();
        SyncDemo02 syncDemo01 = new SyncDemo02();

        //基于相同runnable实现类的Thread
        new Thread(syncDemo02,"线程一").start();
        new Thread(syncDemo01,"线程二").start();
    }

    @Override
    public void run() {
        synchronized(SyncDemo02.class){
            for(int i=0;i<20;i++){
                try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
        }
    }
}
