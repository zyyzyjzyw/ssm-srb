package com.tedu.java;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tedu.java.mapper.ProductMapper;
import com.tedu.java.mapper.UserMapper;
import com.tedu.java.pojo.Product;
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

    @Resource
    private ProductMapper productMapper;


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

    @Test
    public void testSelectPageByAge(){
        Page<User> pageInfo = new Page<>(1,2);
        userMapper.selectPageByPage(pageInfo,20);
        List<User> records = pageInfo.getRecords();
        System.out.println(records);
    }

    @Test
    public void testConcurrentUpdate(){
        //1、小李
        Product p1 = productMapper.selectById(1L);
        //2、小王
        Product p2 = productMapper.selectById(1L);
        //3、小李将价格加了50元，存入了数据库
        p1.setPrice(p1.getPrice() + 50);
        int result1 = productMapper.updateById(p1);
        System.out.println("小李修改结果：" + result1);
        //4、小王将商品减了30元，存入了数据库
        p2.setPrice(p2.getPrice() - 30);
        int result2 = productMapper.updateById(p2);
        System.out.println("小王修改结果：" + result2);
        if(result2==0){
            p2 = productMapper.selectById(1L);
            p2.setPrice(p2.getPrice()-30);
            result2 = productMapper.updateById(p2);
        }
        //最后的结果
        Product p3 = productMapper.selectById(1L);
        System.out.println("最后的结果：" + p3.getPrice());
    }
}
