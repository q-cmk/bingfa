package com.wang.execut;

import com.wang.dao.UserDao;
import com.wang.pojo.User;
import com.wang.utl.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.concurrent.CountDownLatch;

/**
 * @author wqy
 */
public class Worker implements Runnable {

    CountDownLatch readyLatch;
    CountDownLatch startLatch;
    CountDownLatch endLatch;
    User user;

    SqlSession sqlSession;
    UserDao mapper;

    public Worker(CountDownLatch readyLatch, CountDownLatch startLatch, CountDownLatch endLatch, User user) {
        this.readyLatch = readyLatch;
        this.startLatch = startLatch;
        this.endLatch = endLatch;
        this.user = user;
    }

    {
        sqlSession = MyBatisUtils.getSqlSession();
        mapper = sqlSession.getMapper(UserDao.class);
    }
    @Override
    public void run() {

        try {
            System.out.println(Thread.currentThread() + "准备");
            readyLatch.countDown();
            startLatch.await();
            
            int res = mapper.addUser(user);
            if (res>0){
                System.out.println("插入成功");
            }
            sqlSession.commit();
            sqlSession.close();

            System.out.println(Thread.currentThread().getName() + " 到达终点。");
            endLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
