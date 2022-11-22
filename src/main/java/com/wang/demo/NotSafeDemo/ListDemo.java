package com.wang.demo.NotSafeDemo;

import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ArrayList进行读写操作 线程不安全
 * 解决方法 1.使用线程安全类Vector
 *        2.使用Collections.synchronizedList方法得到新的List
 *        3.使用CopyOnWriteArrayList
 * @author wqy
 */
public class ListDemo {
    public static void main(String[] args) {
//        Vector list = new Vector();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());

        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i =0;i<10;i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
/**
 * CopyOnWriteArrayList原理
 * 写时复制
 * CopyOnWrite容器即写时复制的容器，往一个容器添加元素的时候，先获取当前容器的Object[]
 * 然后进行复制，得到一个新的Object[] newElements,新的数组对象长度加一，然后向新的数组
 * 对象中添加元素，最后再将原数组对象的引用指向新的数组对象。这样做的好处是读写分离，可以
 * 并发的读。
 */
