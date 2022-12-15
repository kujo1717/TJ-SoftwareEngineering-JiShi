package com.example.backend.controller;

import com.example.backend.common.Result;
import com.example.backend.dto.FriendDTO;
import com.example.backend.entity.friendGroup;
import com.example.backend.service.FriendService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = {"Friend"})
@RestController
@RequestMapping("friend")
public class FriendController {
    @Autowired
    FriendService friendService;
    @GetMapping("")
    public Result<Map<String,Object>> getUserFriends(@RequestParam Long id){
        Map<String,Object> map = new HashMap<>();
        map.put("friends",friendService.getFriends(id));
        map.put("groups",friendService.getFriendGroups(id));
        return Result.success(map);
    }

    @PostMapping("manage")
    public Result<String> manageFriend(@RequestBody FriendDTO friendDTO){
        System.out.println("传入参数");
        System.out.println(friendDTO);
        if (friendService.setNewFriends(friendDTO)) {
            return Result.success("成功修改");
        }
        return Result.fail(10001,"失败");

    }
}
