package com.example.backend.service;

import com.example.backend.common.Result;
import com.example.backend.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface FriendService {
    Result<String> addFriend(String email);
    Result<List<Map<String,Object>>> getFriends(Long id);
    Result<List<Map<String,Object>>> getFriendGroups(Long id);
}
