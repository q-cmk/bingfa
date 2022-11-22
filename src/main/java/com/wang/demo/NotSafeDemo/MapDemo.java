package com.wang.demo.NotSafeDemo;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wqy
 * hashMap线程不安全
 * 解决方法
 * 1.Collections.synchronizedMap
 * 2.ConcurrentHashMap
 */
public class MapDemo {
    public static void main(String[] args) {
//        HashMap<String, String> map = new HashMap<>();
//        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());

        Map<String,String> map = new ConcurrentHashMap();
        for (int i = 0; i <3;i++){
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
