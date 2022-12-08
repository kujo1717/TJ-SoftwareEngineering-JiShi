package com.example.backend.controller;


import com.example.backend.common.Result;
import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("findUser/{id}")
    public Result<User> findUser(@ApiParam(name="id", value="用户id", required = true)
                                     @PathVariable("id") Long id){
        return userService.findUser(id);
    }
}
