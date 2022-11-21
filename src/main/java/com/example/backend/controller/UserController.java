package com.example.backend.controller;


import com.example.backend.Tools.JwtUtil;
import com.example.backend.common.Result;
import com.example.backend.entity.User;
import com.example.backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    @Autowired
    private HttpServletRequest request;
    private JwtUtil jwtUtil;
    @ApiOperation("根据id返回指定用户")
    @GetMapping("")
    public Result<User> findUser(){
        //System.out.println(jwtUtil.getUserId(request.getHeader("token")));
        Long userid = Long.valueOf(jwtUtil.getUserId(request.getHeader("token")));
        return userService.findUser(userid);
    }
    @GetMapping("logout")
    public Result<String> logout(){
        return Result.success("success");
    }
    @PostMapping("login")
    public Result<String> login(@RequestBody User user){
        //System.out.println("111");
        return userService.confirmUser(user.getEmail(),user.getPassword());
    }
    @PostMapping("register")
    public Result<String> register(@RequestBody User user){
        return userService.registerUser(user.getEmail(),user.getPassword(),user.getName());
    }
    @PutMapping("")
    public Result<User> updateUser(@RequestBody User user){
        Long userid = Long.valueOf(jwtUtil.getUserId(request.getHeader("token")));
        return userService.putUser(userid,user.getName(),user.getIntroduce(),user.getAge());
    }
}
