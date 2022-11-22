package com.wang.demo.FunInterface;

import java.util.function.Supplier;

/**
 * @author wqy
 * 测试函数式接口之
 * 供给型接口 supplier<T>
 */
public class SupplierTest {
    public static void main(String[] args) {
        /**
         * 无参数，返回值类型为T
         */
        Supplier<Integer> s = ()->{
            return 1024;
        };
        //对应方法为get()
        System.out.println(s.get());
    }
}
