package com.example.backend.service.impl;

import com.example.backend.common.Result;
import com.example.backend.entity.User;
import com.example.backend.mapper.FriendMapper;
import com.example.backend.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendMapper friendMapper;
    public Result<String> addFriend(String email){

        return Result.success("已发送请求");
    }
    public Result<List<User>> getFriends(Long id){
        var friends=friendMapper.selectFriend(id);

        return Result.success(friends);
    }
}
