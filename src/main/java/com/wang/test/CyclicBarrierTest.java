package com.wang.test;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 测试CyclicBarrier的使用
 * @author wqy
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.out.println("已经凑够五个人，可以开一局了");
        });
        for (int i = 0; i <10;i++){
            final int id =i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+",id:"+id+"现在正在前往游戏大厅");
                try {
                    Thread.sleep(new Random().nextInt(1000));
                    System.out.println(Thread.currentThread().getName()+",id:"+id+"已经到达大厅");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName()+"开始了游戏");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
