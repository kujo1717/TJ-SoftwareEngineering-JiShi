package com.example.backend.controller;

import com.example.backend.common.Result;
import com.example.backend.entity.DustbinTask;
import com.example.backend.service.DustbinService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author hym
 * @Date $ $
 * @MethodName $
 * @Description $
 * @Return $
 * @Throw $
 */
@Api(tags = {"Dustbin"})
@RestController
@RequestMapping("dustbin")
public class DustbinController {
    @Autowired
    DustbinService dustbinService;

    @ApiOperation("返回所有垃圾桶里的事项")
    @GetMapping("findAllRubbish")
    public Result<List<DustbinTask>> findAllRubbish(){
        return dustbinService.findAllDustbinTask();
    }

    @ApiOperation("根据taskId删除垃圾")
    @DeleteMapping("smashOneRubbish")
    public Result<String> smashOneRubbish(@ApiParam(name="id", value="要永久删除的事项id", required = true)
                                              @RequestParam("id") Long id)
    {
        return dustbinService.smashOneRubbish(id);
    }

    @ApiOperation("清空回收站")
    @DeleteMapping("clearDustbin")
    public Result<String> smashOntRubbish()
    {
        return dustbinService.clearDustbin();
    }

    @ApiOperation("根据taskId恢复事项")
    @PatchMapping("restoreOneRubbish")
    public Result<String> restoreOneRubbish(@ApiParam(name="id", value="要恢复的事项id", required = true)
                                                @RequestParam("id") Long id)
    {
        return dustbinService.restoreOneRubbish(id);
    }
}
