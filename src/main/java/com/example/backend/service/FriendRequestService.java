package com.example.backend.service;

import com.example.backend.dto.FriendRequestDto;
import com.example.backend.entity.friendRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FriendRequestService {
    List<FriendRequestDto> findFriendRequest(Long userid);
    boolean sendFriendRequest(Long userid,String friendEmail,int status);
    boolean sendFriendRequestById(Long userid,Long friendId,int status);
    boolean modifyFriendRequestStatus(Long userid,Long friendid);
}
