package com.example.backend.controller;
import com.example.backend.common.Result;
import com.example.backend.entity.UserMark;
import com.example.backend.mapper.UserMarkMapper;
import com.example.backend.service.UserMarkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RelationSupport;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 杨严
 * @Date: 2022/11/27/10:11 PM
 * @Description:
 */
@Api(tags = {"UserMark"})
@RestController
@RequestMapping("usermark")
public class UserMarkController {
    @Autowired
    UserMarkService userMarkService;

    @ApiOperation("新增一条用户间评价")
    @PostMapping("postUserMark")
    public Result<Map<String,Object>> AddOneUserMark(@RequestBody UserMark userMark){
        Map<String,Object> map=new HashMap<>();
        map.put("user_mark",userMark);
        try{
//            int a=0;
//            System.out.println(a/2);
            userMarkService.AddUserMark(userMark);
            map.put("msg","SUCCESS");
        }catch (Exception e){
            return  Result.fail(HttpStatus.EXPECTATION_FAILED.value(),"AddOneUserMark FAILED");
        }
        return Result.success(map);
    }

}
