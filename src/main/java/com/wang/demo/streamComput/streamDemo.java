package com.wang.demo.streamComput;

import java.util.Arrays;
import java.util.List;

/**
 * @author wqy
 */
public class streamDemo {
    public static void main(String[] args) {
        User u0 = new User(11, "a", 23);
        User u1 = new User(12, "b", 22);
        User u2 = new User(13, "c", 24);
        User u3 = new User(14, "d", 26);
        User u4 = new User(16, "e", 28);

        List<User> users = Arrays.asList(u0, u1, u2, u3, u4);

        //流式计算
        users.stream().filter(t->(t.getId()%2==0))
                      .filter(t->(t.getAge()>24))
                      .map(t->(t.getUserName().toUpperCase()))
                      .sorted((o1,o2)->(o2.compareTo(o1)))
                      .forEach(System.out::println);
    }
}
class User{
    private Integer id;
    private String userName;
    private Integer age;

    public User(Integer id, String userName, Integer age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
