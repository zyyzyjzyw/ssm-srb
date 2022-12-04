package com.tedu.java;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tedu.java.mapper.UserMapper;
import com.tedu.java.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author： zyy
 * @date： 2022/12/4 8:10
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@SpringBootTest
public class InterceptorTest {

    @Resource
    private UserMapper userMapper;


    @Test
    public void testSelectPage(){
        Page<User> userPage = new Page<User>(1, 5);
        userMapper.selectPage(userPage,null);
        List<User> records = userPage.getRecords();
        System.out.println(records);
        boolean b = userPage.hasNext();
        System.out.println("有没有下一页:"+b);
        boolean b1 = userPage.hasPrevious();
        System.out.println("有没有上一页:"+b1);
    }
}
