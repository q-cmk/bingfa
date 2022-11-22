package com.wang.demo.threadPool;

import java.util.concurrent.*;

/**
 * @author wqy
 * 线程池参数
 *     四种拒绝策略：
 *          AbortPolicy(默认):直接抛出RejectedExecutionException异常阻止系统正常运行
 *          callerRunsPolicy:“调用者运行"一种调节机制，该策略既不会抛弃任务，也不会
 *          抛出异常，而是将某些任务回退到调用者，从而降低新任务的流量
 *          DiscardOldestPolicy:抛弃队列中等待最久的任务，然后把当前任务加入队列中
 *          尝试再次提交当前任务。
 *          DiscardPolicy:该策略默默地丢弃无法处理的任务，不予以任何处理也不抛出异常
 *          如果允许任务丢失，这是最好的策略。
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        //逻辑处理器数量
        final int size = Runtime.getRuntime().availableProcessors();
        /**
         * 核心线程数量 corePoolSize
         * 最大线程数量 maximumPoolSize   逻辑处理器数量加一是可使用的最大线程数量
         * 存活时间 keepAliveTime (long)
         * 存放时间的单位 TimeUnit.SECONDS
         * 等待队列 ArrayBlockingQueue
         * 线程工厂 Executors.defaultThreadFactory()    默认工厂
         * 拒绝策略 ThreadPoolExecutor.AbortPolicy()    默认拒绝策略，报异常
         */
        ExecutorService executorService = new ThreadPoolExecutor(
                2, 5,
                2L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        //线程池可承受的最大线程数量是阻塞队列长度加最大线程数
        for(int i = 0; i < 8; i++){
            executorService.execute(()->{
                System.out.println(Thread.currentThread().getName()+"===>正在办理业务");
            });
        }
        executorService.shutdown();

    }
}
