package wang.dao;

import com.wang.utl.Worker;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestInsert {
    public static void main(String[] args) {

        //将列表中的数据并发的插入到数据库中
        ArrayList<ArrayList<String>> arrayLists = new ArrayList<ArrayList<String>>(){
            {
                add(new ArrayList<String>(){
                    {
                        add("大少");
                        add("789");
                    }
                });
                add(new ArrayList<String>(){
                    {
                        add("二少");
                        add("786");
                    }
                });
            }
        };

        int n= arrayLists.size();
        ExecutorService executorService = Executors.newFixedThreadPool(n);

        long startTimes = System.currentTimeMillis();//线程开始时间
        System.out.println(Thread.currentThread().getName()+"发令开始");

        for(int i = 0; i < n; i++) {
            executorService.execute(new Worker(
                    arrayLists.get(i).get(0),
                    arrayLists.get(i).get(1)));

        }
        executorService.shutdown();
        long endTimes = System.currentTimeMillis();
        System.out.println("所有线程执行完毕:" + (endTimes - startTimes)+"毫秒");

    }
}
