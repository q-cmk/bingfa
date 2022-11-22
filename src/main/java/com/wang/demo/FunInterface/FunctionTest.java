package com.wang.demo.FunInterface;

import java.util.function.Function;

/**
 * @author wqy
 * 测试函数式接口之
 *  函数型接口 Function<T,R>
 */
public class FunctionTest {
    public static void main(String[] args) {
        //参数类型为T,返回值类型为R
        Function<String,Integer> f = t->{
            return t.length();
        };
        //对应的方法为apply(T t)
        System.out.println(f.apply("abca"));
    }
}
