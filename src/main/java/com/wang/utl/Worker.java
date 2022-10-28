package com.wang.utl;

import com.wang.dao.UserDao;
import com.wang.pojo.User;
import org.apache.ibatis.session.SqlSession;

import java.util.concurrent.CountDownLatch;

/**
 * @author wqy
 */
public class Worker implements Runnable {


    String name = "";
    String pwd ="";


    SqlSession sqlSession;
    UserDao mapper;

    public Worker( String name, String pwd) {
        this.name = name;
        this.pwd = pwd;

    }

    {
        sqlSession = MyBatisUtils.getSqlSession();
        mapper = sqlSession.getMapper(UserDao.class);
    }
    @Override
    public void run() {

        int res = mapper.addUser(new User(name, pwd));
        sqlSession.commit();
        sqlSession.close();

    }
}
