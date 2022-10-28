package com.wang.utl;

import com.wang.dao.UserDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/*
通过配置文件来获取SqlSessionFactory的对象
再通过SqlSessionFactory获得SqlSession实例
 */
//sqlSessionFactory --> sqlSession
public class MyBatisUtils {
    //提升作用域
    private static SqlSessionFactory sqlSessionFactory;

    //获取SqlSessionFactory的对象
    static {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //通过SqlSessionFactory 获得SqlSession的实例
    //SqlSession 完全包含了向数据库执行SQL命令所需的所有方法
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }

}
