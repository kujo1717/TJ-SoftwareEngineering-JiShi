package com.example.backend.controller;

import com.example.backend.entity.User;
import com.example.backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/user")
    public String query(){
        List<User> list = userMapper.findAll();
        System.out.println(list);
        return "查询用户";
    }
}