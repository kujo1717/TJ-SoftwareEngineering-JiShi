package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.backend.dto.FriendRequestDto;
import com.example.backend.entity.Activity;
import com.example.backend.entity.User;
import com.example.backend.entity.friendRequest;
import com.example.backend.mapper.ActivityMapper;
import com.example.backend.mapper.FriendRequestMapper;
import com.example.backend.mapper.UserMapper;
import com.example.backend.service.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class FriendRequestImpl implements FriendRequestService {
    @Autowired
    FriendRequestMapper friendRequestMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ActivityMapper activityMapper;
    @Override
    public List<FriendRequestDto> findFriendRequest(Long userid) {
        QueryWrapper<friendRequest> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userid",userid);
        List<FriendRequestDto> re=new ArrayList<>();
        var requestList=friendRequestMapper.selectList(queryWrapper);
        for (int i=0;i<requestList.size();i++){
            User user=userMapper.selectById(requestList.get(i).getFriendid());
            System.out.println("获取活动id"+requestList.get(i));
            Activity activity=activityMapper.selectById(requestList.get(i).getActivityId());

            if (activity!=null) {
                re.add(new FriendRequestDto(requestList.get(i), user.getName(), user.getAvatar(), activity.getTitle_name()));
            }
            else
                re.add(new FriendRequestDto(requestList.get(i), user.getName(), user.getAvatar(),null));
        }
        return re;
    }

    @Override
    public boolean sendFriendRequest(Long userid, String friendEmail,Long activity_id,int status) {
        var Now=new Date();
        Long friendId=userMapper.selectByEmail(friendEmail).getId();
        friendRequest friend_request=new friendRequest(friendId,userid,Now,activity_id,status);
        try {
            if (friendRequestMapper.insert(friend_request) > 0) {
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    @Override
    public boolean sendFriendRequestById(Long userid, Long friendid,Long activity_id,int status) {
        var Now=new Date();
        friendRequest friend_request=new friendRequest(userid,friendid,Now,activity_id,status);
        if(friendRequestMapper.insert(friend_request)>0) {
            return true;
        }
        return false;
    }
    @Override
    public boolean modifyFriendRequestStatus(Long userid,Long friendid){
        if(friendRequestMapper.update(null, Wrappers.<friendRequest>lambdaUpdate()
                .set(friendRequest::getStatus,0)
                .eq(friendRequest::getUserid,userid)
                .eq(friendRequest::getFriendid,friendid))>0) {
            return true;
        }
        return false;
    }
}
