java并发实战

1.整合mybatis
在整合mybatis中遇到的问题，当mybatis-config.xml中出现中文注释时或者
在Mapper.xml中文件中出现中文注释时，都会发生报错。
在mybatis-config.xml中注册mapper文件时使用<package name="com.wang.dao"/>
的方式注册，发生了错误，而使用<mapper resource="com/wang/dao/UserMapper.xml"/>
则不发生报错。