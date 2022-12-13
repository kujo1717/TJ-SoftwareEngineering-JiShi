package com.example.backend.controller;

import com.example.backend.common.Result;
import com.example.backend.service.ReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 杨严
 * @Date: 2022/12/13/9:39 AM
 * @Description:
 */
@Api(tags={"Report"})
@RestController
@RequestMapping("report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @ApiOperation("获取待审核的举报单的个数")
    @GetMapping("getPendingNum")
    public Result<Map<String,Object>> getPendingNum(){
        Map<String,Object> map=new HashMap<>();

        return Result.success(map);
    }




}
