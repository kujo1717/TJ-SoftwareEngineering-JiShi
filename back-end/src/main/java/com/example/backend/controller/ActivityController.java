package com.example.backend.controller;

import com.example.backend.common.Result;
import com.example.backend.dto.ActivityBriefDto;
import com.example.backend.dto.ActivityDetailDto;
import com.example.backend.entity.Activity;
import com.example.backend.service.ActivityService;
import com.example.backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 杨严
 * @Date: 2022/11/22/8:46 PM
 * @Description:
 */
@Api(tags = {"Activity"})
@RestController
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation("获取所有活动")
    @GetMapping("/getAll")
    public Result<List<Activity>> getAll(){
        List<Activity> list;
        try {
            list=activityService.getAll();
            return Result.success(list);
        }
        catch (Exception e){
            return Result.fail(HttpStatus.EXPECTATION_FAILED.value(),"getAll failed");
        }
    }

    @ApiOperation("获取用户创建的所有活动的概要")
    @GetMapping("/getUserCreateActBrief/{id}")
    public Result<List<ActivityBriefDto>> getUserActBrief(
            @ApiParam(name="id", value="用户id", required = true)
            @PathVariable(name = "id") Long id){
        List<Activity> list_act=activityService.getUserCreateActList(id);
        List<ActivityBriefDto> list_actbrief=list_act.stream().map(act->
        {return modelMapper.map(act,ActivityBriefDto.class);}).collect(Collectors.toList());

        return Result.success(list_actbrief);
    }

    @ApiOperation("获取一个活动的detail")
    @GetMapping("/getAct/{id}")
    public Result<ActivityDetailDto> getAct(
            @ApiParam(name="id", value="活动id", required = true)
            @PathVariable(name = "id") Long id) {
        System.out.println(id);
        Activity activity;
        try {
            /**获取Activity实体*/
            activity=activityService.getAct(id);


            /**组装活动detail的dto*/
            ActivityDetailDto activityDetailDto=new ActivityDetailDto(activity);


            return Result.success(activityDetailDto);
        }
        catch (Exception e){
            return Result.fail(HttpStatus.EXPECTATION_FAILED.value(),"getAct failed,活动id："+id);
        }

    }


    @ApiOperation("新建活动")
    @PostMapping("/postAct")
    public Result<String> insertOneNewTask(@RequestBody Activity activity)
    {
        System.out.println(activity);
        return activityService.createAct(activity);
    }

    @ApiOperation("更新活动的基本信息")
    @PatchMapping("/patchOneAct")
    public Result<String> patchOneAct(@RequestBody Activity activity)
    {
        System.out.println("activity"+activity);
//        return Result.success("patchOneAct");
        return activityService.UpdateAct(activity);
    }


}
