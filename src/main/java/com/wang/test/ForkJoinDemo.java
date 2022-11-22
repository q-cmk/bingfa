package com.wang.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author wqy
 * 分支合并框架
 *
 * ForkJoinPool
 * ForkJoinTask
 * RecursiveTask
 */
public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyTask myTask = new MyTask(0, 10);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(myTask);
        System.out.println(submit.get());
        forkJoinPool.shutdown();
    }
}
class MyTask extends RecursiveTask<Integer>{

    private static final Integer ADJUST_VALUE = 10;

    private int begin;
    private int end;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    private int result;

    @Override
    protected Integer compute() {
        if((end - begin) <= ADJUST_VALUE){
            for(int i = begin; i <=end; i++){
                result = result +i;
            }
        }else{
            int middle = (end+begin)/2;
            MyTask myTask0 = new MyTask(begin, middle);
            MyTask myTask = new MyTask(middle+1, end);
            myTask0.fork();
            myTask.fork();
            result = myTask0.join()+myTask0.join();
        }
        return result;
    }

}
