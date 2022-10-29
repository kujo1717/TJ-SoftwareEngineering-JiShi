package com.backend.user.controller;

import com.backend.common.Result;
import com.backend.common.user.UserBo;
import com.backend.user.service.UserService;
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
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("findUser/{id}")
    public Result<UserBo> findUser(@PathVariable("id") Long id){
        return userService.findUser(id);
    }
}
