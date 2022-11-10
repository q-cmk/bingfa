package com.wang.service;

import com.wang.execut.Callers;
import com.wang.pojo.User;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImplTest extends TestCase {
    public void test(){
        UserServiceImpl userService = new UserServiceImpl();
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        List<User> users = userService.findUserById(ids);
        users.forEach(System.out::println);
//        ArrayList<User> users = new ArrayList<>();
//        User user0 = new User("三少爷", "897");
//        User user1 = new User("三少爷", "896");
//        users.add(user0);
//        users.add(user1);
//        userService.addUsers(users);
//        Callers callers = new Callers();
//        callers.insert(user0);
    }
}