package wang.dao;

import com.wang.pojo.User;
import com.wang.service.UserServiceImpl;

import java.util.ArrayList;

public class TestServiceImpl {
    public static void main(String[] args) {

        ArrayList<User> users = new ArrayList<>();
        users.add(new User("小兰","231"));
        users.add(new User("小风","314"));
        UserServiceImpl userService = new UserServiceImpl();
        userService.insert(users);
    }
}
