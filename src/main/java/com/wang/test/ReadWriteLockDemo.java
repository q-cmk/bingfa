package com.wang.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author wqy
 * 使用读写锁
 *  读操作过程中可以其它线程只可以读取
 *  写入时，其它线程不能操作
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 0; i <5;i++){
            final int tempInt =i;
            new Thread(()->{
                myCache.put(tempInt+"","");
            },String.valueOf(i)).start();
        }
        for (int i = 0; i <5;i++){
            final int tempInt =i;
            new Thread(()->{
                myCache.get(tempInt+"");
            },String.valueOf(i)).start();
        }
    }
}
class MyCache{
     Map<String,String> map = new HashMap<>();
     ReadWriteLock myLock = new ReentrantReadWriteLock();
     void put(String key, String value){
         myLock.writeLock().lock();
         try {
             System.out.println(Thread.currentThread().getName()+"写入数据..");
             try { TimeUnit.MILLISECONDS.sleep(new Random().nextInt(300)); } catch (InterruptedException e) { e.printStackTrace(); }
             map.put(key, value);
             System.out.println(Thread.currentThread().getName()+"写入成功");
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             myLock.writeLock().unlock();
         }
     }
     void get(String key){
         myLock.readLock().lock();
         try {
             System.out.println(Thread.currentThread().getName()+"读取数据");
             try { TimeUnit.MICROSECONDS.sleep(new Random().nextInt(300)); } catch (InterruptedException e) { e.printStackTrace(); }
             String res = map.get(key);
             System.out.println(Thread.currentThread().getName()+"读取成功"+res);
         } catch (Exception e) {
             e.printStackTrace();
         } finally {
             myLock.readLock().unlock();
         }
     }

}