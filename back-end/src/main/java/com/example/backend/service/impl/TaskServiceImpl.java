package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.backend.common.Result;
import com.example.backend.entity.Task;
import com.example.backend.entity.User;
import com.example.backend.mapper.TaskMapper;
import com.example.backend.mapper.UserMapper;
import com.example.backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskMapper taskMapper;

    @Override
    public Result<Task> findTask(Long task_id) {
        Task task = taskMapper.selectById(task_id);//利用mybatis-plus的单表查询，自己不用写SQL语句
        //没有找到，返回错误码
        if(task == null){
            return Result.fail(404,"事项不存在");
        }

        //找到用户，返回正确信息
        return Result.success(task);
    }

    @Override
    public Result<List<Task>> findTaskByMonth(int year, int month) {
        List<Task> taskList = taskMapper.selectByMonth(year, month);
        return Result.success(taskList);
    }

    @Override
    public Result<List<Task>> findAll() {
        List<Task> taskList = taskMapper.selectList(null);
        return Result.success(taskList);
    }

    @Override
    public Result<Task> findOneTaskAndRelative(Long task_id)
    {
        //因为做了join，一个task_id会对应好几项，所以这里用的是List
        List<Task> taskList = taskMapper.selectOneTaskAndRelative(task_id);
        if(taskList == null || taskList.size() == 0) {
            System.out.println(taskList.toString());
            return Result.fail(404, "找不到该事项！");
        }
        Task taskResult = taskList.get(0);
        //把相同的taskId进行合并，就是合并它们的relativeTask
        if(taskList.size() > 1)
        {
            for(int i=1; i<taskList.size(); i++)
            {
                taskResult.addOneRelativeTask(taskList.get(i).getRelativeTask().get(0));
            }
        }
        return Result.success(taskResult);
    }

    @Override
    public Result<List<Task>> findAllTaskAndRelative() {
        List<Task> taskList = taskMapper.selectAllTaskAndRelative();


        List<Task> taskListResult = new ArrayList<>();
        List<Long> okIdList = new ArrayList<>();

        Task currentTask = null;
        for(Task t : taskList)
        {
            if(!okIdList.contains(t.getTaskId()))
            {
                if (currentTask != null)
                    taskListResult.add(currentTask);
                currentTask = t;
            }
            //把相同的taskId进行合并，就是合并它们的relativeTask
            else
                currentTask.addOneRelativeTask(t.getRelativeTask().get(0));
        }
        if (currentTask != null)
            taskListResult.add(currentTask);
        return Result.success(taskListResult);
    }

    @Override
    public Result<String> insertOneNewTask(Task task) {
        int newID = taskMapper.insert(task);
        if(newID == Integer.MIN_VALUE + 1001)
            return Result.fail(500,"插入数据失败！");

        return Result.success("插入数据成功！");
    }

    @Override
    public Result<String> deleteById(Long id)
    {
        int resultCount = taskMapper.deleteById(id);
        if(resultCount == 0)
            return Result.fail(500,"删除数据失败！");
        return Result.success("删除数据成功！");
    }

    @Override
    public Result<String> patchOneTask(Task task) {
        int resultCount = taskMapper.updateById(task);
        if(resultCount == 0)
            return Result.fail(500,"更新数据失败！");
        return Result.success("更新数据成功！");
    }


}
