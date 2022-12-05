package com.example.backend.service.impl;

import com.example.backend.common.Result;
import com.example.backend.entity.User;
import com.example.backend.entity.friendGroup;
import com.example.backend.mapper.FriendGroupMapper;
import com.example.backend.mapper.FriendMapper;
import com.example.backend.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    FriendMapper friendMapper;
    @Autowired
    FriendGroupMapper friendGroupMapper;
    public Result<String> addFriend(String email){

        return Result.success("已发送请求");
    }
    public Result<List<Map<String,Object>>> getFriends(Long id){
        var friends=friendMapper.selectFriend(id);
        return Result.success(friends);
    }
    Result<List<Map<String,Object>>> getFriendGroups(Long id){
        friendGroup friend_group=new friendGroup();
        friend_group.setBelongId(id);
        var friendgroups= friendGroupMapper.selectList(friend_group);
    }
}
