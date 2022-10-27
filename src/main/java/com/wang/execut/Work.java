package com.wang.execut;

import com.wang.dao.UserDao;
import com.wang.pojo.User;
import com.wang.utl.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class Work implements Runnable {
    public void run() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> userList = mapper.getUserList();
        for (User user : userList) {
            System.out.println(user.toString());
        }
    }
}
