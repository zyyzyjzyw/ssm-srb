package com.tedu.java;

import com.tedu.java.mapper.UserMapper;
import com.tedu.java.pojo.User;
import com.tedu.java.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author： zyy
 * @date： 2022/12/3 16:29
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@SpringBootTest
public class ServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;


    @Test
    public void testCount(){
        int count = userService.count();
        System.out.println("总记录数:"+count);
    }

    @Test
    public void testSaveBatch(){
        ArrayList<User> users = new ArrayList<>();
        for (int i=0;i<5;i++){
            User user = new User();
            user.setName("花花"+i);
            user.setAge(20+i);
            user.setEmail("123456"+String.valueOf(i)+"@qq.com");
            users.add(user);
        }
        boolean b = userService.saveBatch(users);
        System.out.println(b);
    }

    @Test
    public void testSelectListByName(){
        List<User> users = userMapper.selectAllByName("Tom");
        System.out.println(users);
    }
}
