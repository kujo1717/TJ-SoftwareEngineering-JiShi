package com.example.backend.service;


import com.example.backend.common.Result;
import com.example.backend.entity.User;
import org.springframework.stereotype.Service;

/**
 * @Author hym
 * @Date 2022/10/29
 * @Description demo
 */
@Service
public interface UserService {
    User findUserById(Long userId);
}
