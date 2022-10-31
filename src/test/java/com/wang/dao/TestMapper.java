package com.wang.dao;
import com.wang.pojo.User;
import com.wang.utl.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TestMapper {
    @Test
    public void test(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        List<User> userList = mapper.getUserList();
        userList.forEach(System.out::println);


//        int res = mapper.addUser(new User( "小红", "234"));
//        if(res>0){
//            System.out.println("插入成功");
//        }
//        //提交事务
//        sqlSession.commit();
        sqlSession.close();
    }
}
