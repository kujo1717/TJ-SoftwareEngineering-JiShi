package com.example.backend.service.impl;


import com.example.backend.Tools.JwtUtil;
import com.example.backend.common.Result;
import com.example.backend.entity.DTO.UserDTO;
import com.example.backend.entity.User;
import com.example.backend.mapper.UserMapper;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author hym
 * @Date 2022/10/29
 * @Description demo
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public Result<User> findUser(Long id){
        User user = userMapper.selectById(id);//利用mybatis-plus的单表查询，自己不用写SQL语句
        //没有找到用户，返回错误码
        if(user == null){
            return Result.fail(10001,"用户不存在");
        }

        //找到用户，返回正确信息
        return Result.success(user);
    }
    public Result<String> confirmUser(String email,String password){
        User user=userMapper.selectByEmail(email);
        if (user==null){
            return Result.fail(10001,"用户不存在");
        }
        else if (!user.getPassword().equals(password)){
            return Result.fail(10001,"密码错误");
        }
        String userId=user.getId().toString();
        String token = JwtUtil.sign(userId);
        return Result.success(token);
    }
    public Result<String> registerUser(String email,String password,String name){
        User user=userMapper.selectByEmail(email);
        if (user!=null){
            return Result.fail(10001,"用户已存在");
        }
        if(userMapper.insertUser(name,email,password)>0){
            user=userMapper.selectByEmail(email);
            String userId=user.getId().toString();
            String token = JwtUtil.sign(userId);
            return Result.success(token);
        }

        return Result.fail(10001,"注册失败");
    }
    public Result<User> putUser(Long id,String name,String introduce,int age){
        int i=userMapper.updateUser(id,name,age,introduce);
        if (i>0){
            return Result.success(userMapper.selectById(id));
        }
        else{
            return Result.fail(10001,"更新失败！");
        }
    }

}
