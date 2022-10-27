package com.wang.utl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author wqy
 */
public class ConcurrentUtil {

//    public static <T> Future<T> doJob(ExecutorService executorService, Callable<T> callable) {
//        return executorService.submit(callable);
//    }

    public static ExecutorService  getExecutorService(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        return executorService;
    }
}
