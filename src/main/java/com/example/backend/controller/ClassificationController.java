package com.example.backend.controller;

import com.example.backend.Dto.SortedTask;
import com.example.backend.common.Result;
import com.example.backend.entity.Classification;
import com.example.backend.entity.Task;
import com.example.backend.service.ClassificationService;
import com.example.backend.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author hym
 * @Date $ $
 * @MethodName $
 * @Description $
 * @Return $
 * @Throw $
 */
@Api(tags = {"Classification"})
@RestController
@RequestMapping("classification")
public class ClassificationController {
    @Autowired
    ClassificationService classificationService;

    @Autowired
    TaskService taskService;

    @ApiOperation("获取该用户的所有分类名")
    @GetMapping("getOneUserAllClassificationTitle")
    public Result<List<Classification>> findOneUserAllClassificationTitle(@ApiParam(name="userId", value="要查找的用户id", required = true)
                                                                              @RequestParam("userId") Long userId)
    {
        try
        {
            List<Classification> classList = classificationService.findOneUserAllClassificationTitle(userId);
            return Result.success(classList);
        }
        catch (Exception e)
        {
            return Result.fail(500,e.getMessage());
        }

    }

    @ApiOperation("获取该用户的所有分类事项")
    @GetMapping("getOneUserAllSortedTask")
    public Result<List<SortedTask>> findOneUserAllSortedTask(@ApiParam(name="userId", value="要查找的用户id", required = true)
                                                                 @RequestParam("userId") Long userId)
    {
        try
        {
            //获取该用户的所有分类
            List<Classification> classificationList = classificationService.findOneUserAllClassificationTitle(userId);
            List<SortedTask> sortedTaskList = new ArrayList<>();//最终返回的结果

            //遍历每个分类
            for (Classification classification : classificationList) {
                String classificationTitle = classification.getClassificationTitle();
                List<Task> taskList = taskService.selectOneUserOneSortAllTask(userId, classificationTitle);

                SortedTask newSortedTask = new SortedTask(classificationTitle, taskList);
                sortedTaskList.add(newSortedTask);
            }
            return Result.success(sortedTaskList);
        }
        catch (Exception e)
        {
            return Result.fail(500,e.getMessage());
        }
    }

    @ApiOperation("用户添加一个新的分组")
    @PostMapping("postOneClassification")
    public Result<String> postOneClassification(@ApiParam(name="userId", value="用户id", required = true)
                                                    @RequestParam("userId") Long userId,
                                                @ApiParam(name="classificationTitle", value="要添加的分组名称", required = true)
                                                @RequestParam("classificationTitle") String classificationTitle)
    {
        try
        {
            int res = classificationService.insertOneClassification(userId, classificationTitle);
            return Result.success("新分组创建成功！");
        }
        catch (Exception e)
        {
            return Result.fail(500,e.getMessage());
        }
    }
}
