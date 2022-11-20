package com.example.backend.controller;

import com.example.backend.common.Result;
import com.example.backend.entity.Task;
import com.example.backend.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
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

@Api(tags = {"Task"})
@RestController
@RequestMapping("task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @ApiOperation("根据id返回指定事项")
    @GetMapping("findtask")
    public Result<Task> findUser(@ApiParam(name="id", value="事项id", required = true)
                                 @RequestParam("id") Long id){
        return taskService.findTask(id);
    }

    @ApiOperation("根据年、月信息返回事项列表")
    @GetMapping("findmonth")
    public Result<List<Task>> findTaskListByMonth(@ApiParam(name="year", value="事项在某个year能够出现", required = true)
                                                  @RequestParam("year") int year,
                                                  @ApiParam(name="month", value="事项在某个month能够出现", required = true)
                                                  @RequestParam("month") int month)
    {
        return taskService.findTaskByMonth(year, month);
    }

    @ApiOperation("返回task表的所有数据")
    @GetMapping("findall")
    public Result<List<Task>> findAllTask()
    {
        return taskService.findAll();
    }

}
