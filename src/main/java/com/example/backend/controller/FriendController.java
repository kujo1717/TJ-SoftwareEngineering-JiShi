package com.example.backend.controller;

import com.example.backend.common.Result;
import com.example.backend.service.FriendService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(tags = {"Friend"})
@RestController
@RequestMapping("friend")
public class FriendController {
    @Autowired
    FriendService friendService;
    @GetMapping("")
    public Result<List<Map<String,Object>>> getUserFriends(@RequestParam Long id){
        return friendService.getFriends(id);
    }
    @GetMapping("group")
    public Result<List<Map<String,Object>>> getUserFriendGroups(@RequestParam Long id){

    }
}
