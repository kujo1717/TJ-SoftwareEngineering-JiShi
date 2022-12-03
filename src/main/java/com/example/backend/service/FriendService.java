package com.example.backend.service;

import com.example.backend.common.Result;
import com.example.backend.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FriendService {
    Result<String> addFriend(String email);
    Result<List<User>> getFriends(Long id);
}
