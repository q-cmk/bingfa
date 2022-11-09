package com.wang.execut;

import com.wang.dao.UserDao;
import com.wang.pojo.User;
import com.wang.utl.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

/**
 * @author wqy
 */
public class Callers {
    SqlSession sqlSession;
    UserDao mapper;

    {
        sqlSession = MyBatisUtils.getSqlSession();
        mapper = sqlSession.getMapper(UserDao.class);
    }
    public  User call(Integer id) {
        User user = mapper.selectUserbyId(id);
        sqlSession.close();
        return user;
    }
    public void insert(User user) {
        int i = mapper.addUser(user);

        if(i>0){
            System.out.println(Thread.currentThread().getName()+"插入用户成功");
        }
        sqlSession.commit();
        sqlSession.close();
    }
}
