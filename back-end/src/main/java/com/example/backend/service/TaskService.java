package com.example.backend.service;

import com.example.backend.common.Result;
import com.example.backend.entity.Task;
import org.springframework.stereotype.Service;

/**
 * @Author hym
 * @Date 2022/11/19
 * @Description 事项Service
 */
@Service
public interface TaskService {
    Result<Task> findTask(Long task_id);
}
