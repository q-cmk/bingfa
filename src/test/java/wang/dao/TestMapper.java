package wang.dao;
import com.wang.dao.UserDao;
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
//        List<User> userList = mapper.getUserList();
//        for (User user : userList) {
//            System.out.println(user.toString());
//        }

//        User user = mapper.selectUserbyId(3);
//        System.out.println(user.toString());

        int res = mapper.addUser(new User( "小红", "234"));
        if(res>0){
            System.out.println("插入成功");
        }
        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }
}
