package com.tedu.java.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tedu.java.pojo.User;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> ListAllByName(String name);
}
