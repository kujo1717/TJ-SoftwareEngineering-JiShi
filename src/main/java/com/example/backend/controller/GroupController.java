package com.example.backend.controller;

import com.example.backend.common.Result;
import com.example.backend.entity.Group;
import com.example.backend.entity.User;
import com.example.backend.service.GroupService;
import com.example.backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/11/25
 * @JDKVersion 17.0.4
 */
@Api(tags = {"Group"})
@RestController
@RequestMapping("Group")
public class GroupController {
    @Autowired
    GroupService groupService;

    @Autowired
    UserService userService;

    @ApiOperation("获取某一个用户在群聊中的所有联系人")
    @GetMapping("getOneGroupAllUser")
    public Result<List<User>> findOneGroupAllUser(@ApiParam(name = "groupId", value = "要查找的群聊id", required = true)
                                                  @RequestParam("groupId") Long groupId,
                                                  @ApiParam(name = "userId", value = "用户Id", required = true)
                                                  @RequestParam("userId") Long userId) {
        try {
            ArrayList<User> users = new ArrayList<>();
            List<Group> oneGroupAllUser = groupService.findOneGroupAllUser(groupId);
            for (Group g : oneGroupAllUser) {
                // 排除自身的数据
                if(!Objects.equals(g.getUserId(), userId)) {
                    User user1 = userService.findUserById(g.getUserId());
                    users.add(user1);
                }
            }

            return Result.success(users);
        } catch (Exception e) {
            return Result.fail(500, e.getMessage());
        }

    }
}
