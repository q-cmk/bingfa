package com.wang.demo.FunInterface;

import java.util.function.Consumer;

/**
 * @author wqy
 * 测试函数式接口
 *  消费性接口 Consumer<T>
 */
public class ConsumerTest {
    public static void main(String[] args) {
        /**
         * 参数类型为T,无返回值
         */
        Consumer<String> c = t->{
            System.out.println(t);
        };
        //对应方法为accept(T t)
        c.accept("我是消费型接口的实现方法");
    }
}
