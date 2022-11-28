package com.example.backend.controller;


import com.example.backend.common.Result;
import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author hym
 * @Date 2022/10/29
 * @Description demo
 */
@Api(tags = {"User"})
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("根据id返回指定用户")
    @GetMapping("finduser")
    public Result<User> findUser(@ApiParam(name = "userId", value = "用户id", required = true)
                                 @RequestParam("userId") Long userId) {
        User user = userService.findUserById(userId);
        //没有找到用户，返回错误码
        if (user == null) {
            return Result.fail(10001, "用户不存在");
        }

        //找到用户，返回正确信息
        return Result.success(user);
    }
}
