package com.tedu.java.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tedu.java.mapper.UserMapper;
import com.tedu.java.pojo.User;
import com.tedu.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author： zyy
 * @date： 2022/12/3 11:58
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> ListAllByName(String name) {
        return userMapper.selectAllByName(name);
    }
}
