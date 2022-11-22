package com.wang.demo.blockQueueDemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author wqy
 * 	    抛出异常 		特殊值 		阻塞 		超时
 * 插入 	add(e) 			offer(e) 	put(e) 		offer(e, time, unit)
 * 移除 	remove() 		poll() 		take() 		poll(time, unit)
 * 检查 	element() 		peek() 		不可用 		不可用
 */
public class ArrayBlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        queue.add("a");
        queue.add("b");
        queue.add("c");
//        queue.add("d");
        queue.remove();
        queue.remove();
        queue.remove();
//        queue.remove();
    }
}
