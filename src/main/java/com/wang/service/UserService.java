package com.wang.service;

import com.wang.pojo.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wqy
 */
public interface UserService {
    /**
     * 并发插入多个用户
     * @param users
     */
    void addUsers(ArrayList<User> users);

    /**
     * 并发查询多个用户
     * @param ids
     * @return
     */
    List<User> findUserById(List<Integer> ids);

    void delete(List<Integer> ids);
}
