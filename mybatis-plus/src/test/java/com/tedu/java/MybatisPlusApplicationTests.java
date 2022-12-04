package com.tedu.java;

import com.tedu.java.mapper.UserMapper;
import com.tedu.java.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Test
   public void TestMPSelect() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void testDeleted(){
        int i = userMapper.deleteById(2);
        System.out.println(1);
    }

}
