package com.wang.demo.synchronizedDemo;

import java.util.concurrent.TimeUnit;

/**
 * @author wqy
 *  用于测试synchronized的用法
 *  用于修饰当前对象
 *  输出结果，如果是基于同一个实现了Runnable接口的实例对象
 *  而生成的线程对象，则可以实现同步，反之则不行。
 */
public class SyncDemo01 implements Runnable {

    @Override
    public void run() {
        //对象锁
        synchronized(this){
            for(int i=0; i<20;i++){
                //睡一会
                try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
        }
    }

    public static void main(String[] args) {
        SyncDemo01 syncDemo01 = new SyncDemo01();
        SyncDemo01 syncDemo02 = new SyncDemo01();

        //基于相同runnable实现类对象的Thread
//        new Thread(syncDemo01, "线程一").start();
//        new Thread(syncDemo01, "线程二").start();

        //基于不同runnable实现类对象的Thread
        new Thread(syncDemo01, "线程三").start();
        new Thread(syncDemo02, "线程四").start();

    }
}
