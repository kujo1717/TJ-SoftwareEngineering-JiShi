package com.example.backend.controller;

import com.example.backend.common.Result;
import com.example.backend.entity.Task;
import com.example.backend.service.TaskService;
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
 * @Date $ $
 * @MethodName $
 * @Description $
 * @Return $
 * @Throw $
 */

@Api(tags = {"Task"})
@RestController
@RequestMapping("task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @ApiOperation("根据id返回指定事项")
    @GetMapping("findtask/{id}")
    public Result<Task> findUser(@ApiParam(name="id", value="事项id", required = true)
                                 @PathVariable("id") Long id){
        return taskService.findTask(id);
    }
}
