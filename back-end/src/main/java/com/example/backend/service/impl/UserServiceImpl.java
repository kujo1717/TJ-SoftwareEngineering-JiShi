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


    public Result<User> findUser(Long id){
        User user = userMapper.selectById(id);//利用mybatis-plus的单表查询，自己不用写SQL语句
        //没有找到用户，返回错误码
        if(user == null){
            return Result.fail(10001,"用户不存在");
        }

        //找到用户，返回正确信息
        return Result.success(user);
    }
}
