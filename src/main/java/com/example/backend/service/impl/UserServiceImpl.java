package com.example.backend.service.impl;


import com.example.backend.common.Result;
import com.example.backend.entity.User;
import com.example.backend.mapper.UserMapper;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author hym
 * @Date 2022/10/29
 * @Description demo
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserById(Long userId) {
        User user = userMapper.selectUserById(userId);
        return user;
    }
}
