package com.tedu.java.controller;

import com.tedu.java.pojo.User;
import com.tedu.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author： zyy
 * @date： 2022/12/5 20:43
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //@CrossOrigin
    @GetMapping("/list")
    public List<User> getUserList(){
        return userService.list();
    }
}
