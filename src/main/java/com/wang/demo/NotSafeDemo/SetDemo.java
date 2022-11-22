package com.wang.demo.NotSafeDemo;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * hashSet线程不安全
 * @author wqy
 */
public class SetDemo {
    public static void main(String[] args) {
//        HashSet<String> set = new HashSet<>();
//        Set<String> set = Collections.synchronizedSet(new HashSet<String>());

        Set<String> set = new CopyOnWriteArraySet<>();

        for(int i=0;i<3;i++){
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0, 8));
            },String.valueOf(i)).start();
        }
    }
}
