package wang.dao;

import com.wang.dao.UserDao;
import com.wang.pojo.User;
import com.wang.utl.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static com.wang.utl.ConcurrentUtil.getExecutorService;

public class Main {

    final static CountDownLatch downLatch = new CountDownLatch(2);

    public static User call(int i) throws Exception {
        System.out.println(Thread.currentThread().getName());
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user = mapper.selectUserbyId(i);
        sqlSession.close();
        downLatch.countDown();
        return user;
    }

    public static void main(String[] args) {
        ExecutorService executorService = getExecutorService();
        List<Callable<User>> callables = new ArrayList<Callable<User>>();

        callables.add(()->call(1));
        callables.add(()->call(3));

        try {
            long startTime = System.currentTimeMillis();     //获取开始时间
            List<Future<User>> futures = executorService.invokeAll(callables);
            downLatch.await();
            long overTime = System.currentTimeMillis();     //获取开始时间
            System.out.println("程序运行时间为："+(overTime-startTime)+"毫秒");
            for (Future<User> future : futures) {
                User user = future.get();
                System.out.println(user.toString());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
