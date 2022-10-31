package com.wang.service;

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
    }
}