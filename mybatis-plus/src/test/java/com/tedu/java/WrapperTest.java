package com.tedu.java;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.tedu.java.mapper.UserMapper;
import com.tedu.java.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author： zyy
 * @date： 2022/12/4 9:10
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@SpringBootTest
public class WrapperTest {

    @Resource
    private UserMapper userMapper;

    /**
     * 查询名字中包含n，年龄大于等于10且小于等于20，email不为空的用户
     */
    @Test
    public void test1(){
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.like("name","n");
        wrapper.between("age",10,20).isNotNull("email");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }

    /**
     * 按年龄降序查询用户，如果年龄相同则按id升序排列
     */
    @Test
    public void test2(){
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.orderByDesc("age").orderByAsc("id");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);
    }

    /**
     * 删除email为空的用户
     */
    @Test
    public void test3(){
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.isNotNull("email");
        int delete = userMapper.delete(wrapper);
        System.out.println(delete);
    }

    /**
     * 查询名字中包含n，且（年龄小于18或email为空的用户），并将这些用户的年龄设置为18，邮箱设置为 user@atguigu.com
     */
    @Test
    public void test4(){
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper
                .like("name","n")
                .and(i->i.lt("age",18).or().isNull("email"));
        User user = new User();
        user.setAge(18);
        user.setEmail("user@atguigu.com");
        int update = userMapper.update(user, wrapper);
        System.out.println(update);
    }
    /**
     * 查询所有用户的用户名和年龄
     */
    @Test
    public void test5(){
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.select("name","age");
        /*List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);*/
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        System.out.println(maps);
    }

    /**
     * 查询id不大于3的所有用户的id列表
     */
    @Test
    public void test6(){
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.inSql("id","select id from user where id <= 3");
        userMapper.selectList(wrapper);
    }
    /**
     * 查询名字中包含n，且（年龄小于18或email为空的用户），并将这些用户的年龄设置为18，邮箱设置为 user@atguigu.com
     */
    @Test
    public void test7(){
        UpdateWrapper<User> wrapper = new UpdateWrapper<User>();
        wrapper
                .set("age",18)
                .set("email","user@atguigu.com")
                .like("name","n")
                .and(i->i.lt("age",18).or().isNull("email"));
        /*User user = new User();
        user.setAge(18);
        user.setEmail("user@atguigu.com");*/
        User user = new User();
        int update = userMapper.update(user, wrapper);
        System.out.println(update);
    }

    /**
     * 查询名字中包含n，年龄大于10且小于20的用户，查询条件来源于用户输入，是可选的
     */
    @Test
    public void test8(){
        String name ="n";
        Integer ageBegin =10;
        Integer ageEnd = 20;
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        /*if(!StringUtils.isBlank(name)){
            wrapper.like("name",name);
        }
        if(ageBegin!=null){
            wrapper.ge("age",ageBegin);
        }
        if(ageEnd!=null){
            wrapper.le("age",ageEnd);
        }*/
        wrapper.like(StringUtils.isBlank(name),"name",name);
        wrapper.ge(ageBegin!=null,"age",ageBegin);
        wrapper.le(ageEnd!=null,"age",ageEnd);
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users);


    }
}
