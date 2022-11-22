package com.wang.demo.threadSynTool;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author wqy
 */
public class ExchangerTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Exchanger exchange = new Exchanger();

        executorService.execute(()->{
            String data = "data0";
            try {
                doExchangeWork(data,exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(()->{
            String data = "data1";
            try {
                doExchangeWork(data,exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        executorService.shutdown();

    }
    private static void doExchangeWork(String data, Exchanger exchanger) throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"正在把数据"+data+"交换出去");
        TimeUnit.MILLISECONDS.sleep(300);
        String exchangeData = (String)exchanger.exchange(data);
        System.out.println(Thread.currentThread().getName()+"交换得到数据"+exchangeData);
    }

}
