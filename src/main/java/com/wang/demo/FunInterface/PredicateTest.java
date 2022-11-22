package com.wang.demo.FunInterface;

import java.util.function.Predicate;

/**
 * @author wqy
 * 测试函数式接口之
 *  判断型接口 Predicate<T>
 */
public class PredicateTest {
    public static void main(String[] args) {
        //参数类型为T,返回值为布尔类型
        Predicate<String> p = t->{
            return t.isEmpty();
        };
        //对应的方法名为test
        p.test("cabc");
    }
}
