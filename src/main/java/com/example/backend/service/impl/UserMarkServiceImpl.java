package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.entity.UserMark;
import com.example.backend.mapper.UserMarkMapper;
import com.example.backend.service.UserMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 杨严
 * @Date: 2022/11/27/9:56 PM
 * @Description:
 */
@Service
public class UserMarkServiceImpl implements UserMarkService {

    @Autowired
    UserMarkMapper userMarkMapper;

    @Override
    public Integer AddUserMark(UserMark userMark) {

        return userMarkMapper.insert(userMark);
    }

    @Override
    public List<UserMark> GetUserAllMarked(Long user_id) {
        List<UserMark> userMarkList=new ArrayList<>();
        QueryWrapper<UserMark> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",user_id);
        userMarkList=userMarkMapper.selectList(queryWrapper);

        return userMarkList;
    }
}
