package com.example.backend.service.impl;

import com.example.backend.common.Result;
import com.example.backend.entity.Task;
import com.example.backend.entity.User;
import com.example.backend.mapper.TaskMapper;
import com.example.backend.mapper.UserMapper;
import com.example.backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        //没有找到用户，返回错误码
        if(task == null){
            return Result.fail(404,"用户不存在");
        }

        //找到用户，返回正确信息
        return Result.success(task);
    }
}
