package com.example.backend.service;


import com.example.backend.common.Result;
import com.example.backend.entity.DTO.UserDTO;
import com.example.backend.entity.User;
import org.springframework.stereotype.Service;

/**
 * @Author hym
 * @Date 2022/10/29
 * @Description demo
 */
@Service
public interface UserService {
    Result<User> findUser(Long id);
    Result<String> confirmUser(String email, String password);
    Result<String> registerUser(String email,String password,String name);
    public Result<User> putUser(Long id,String name,String introduce,int age);
}