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

    @ApiOperation("根据id返回指定事项（不带亲戚）")
    @GetMapping("findtask")
    public Result<Task> findUser(@ApiParam(name="id", value="事项id", required = true)
                                 @RequestParam("id") Long id){
        return taskService.findTask(id);
    }

    @ApiOperation("根据年、月信息返回事项列表（不带亲戚）")
    @GetMapping("findmonth")
    public Result<List<Task>> findTaskListByMonth(@ApiParam(name="year", value="事项在某个year能够出现", required = true)
                                                  @RequestParam("year") int year,
                                                  @ApiParam(name="month", value="事项在某个month能够出现", required = true)
                                                  @RequestParam("month") int month)
    {
        return taskService.findTaskByMonth(year, month);
    }

    @ApiOperation("返回task表的所有数据（不带亲戚）")
    @GetMapping("findall")
    public Result<List<Task>> findAllTask()
    {
        return taskService.findAll();
    }

    @ApiOperation("返回一个task和它的所有亲戚")
    @GetMapping("findOneTaskAndRelative/{id}")
    public Result<Task> findTaskAndRelative(@ApiParam(name="id", value="事项id", required = true)
                                                @PathVariable("id") Long id)
    {
        return taskService.findOneTaskAndRelative(id);
    }

    @ApiOperation("返回所有task和它的所有亲戚")
    @GetMapping("findAllTaskAndRelative")
    public Result<List<Task>> findAllTaskAndRelative()
    {
        return taskService.findAllTaskAndRelative();
    }

    @ApiOperation("插入一条新的task")
    @PostMapping("addOneNewTask")
    public Result<String> insertOneNewTask(@RequestBody Task task)
    {
        return taskService.insertOneNewTask(task);
    }

    @ApiOperation("更新事项")
    @PatchMapping("patchOneTask")
    public Result<String> patchOneTask(@RequestBody Task task)
    {
        return taskService.patchOneTask(task);
    }

    @ApiOperation("根据ID删除事项")
    @DeleteMapping("removeOneTask")
    public Result<String> removeOneTask(@ApiParam(name="id", value="要删除的事项id", required = true)
                                            @RequestParam("id") Long id)
    {
        return taskService.deleteById(id);
    }

}
