package com.wang.dao;

import com.wang.pojo.User;

import java.util.List;

/**
 * @author wqy
 */
public interface UserDao {

    //insert一个用户
    int addUser(User user);

    /**
     *
     * @return
     */
    List<User> getUserList();

    User selectUserbyId(int id);
}
