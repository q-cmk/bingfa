package com.wang.service;

import com.wang.pojo.User;

import java.util.ArrayList;

/**
 * @author wqy
 */
public interface Service {
    /**
     * 并发插入多个用户
     * @param users
     */
    void insert(ArrayList<User> users);
}
