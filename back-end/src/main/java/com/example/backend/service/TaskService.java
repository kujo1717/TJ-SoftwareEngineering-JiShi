package com.example.backend.service;

import com.example.backend.common.Result;
import com.example.backend.entity.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author hym
 * @Date 2022/11/19
 * @Description 事项Service
 */
@Service
public interface TaskService {
    Result<Task> findTask(Long task_id);
    Result<List<Task>> findTaskByMonth(int year, int month);
    Result<List<Task>> findAll();
    Result<Task> findOneTaskAndRelative(Long task_id);
    Result<List<Task>> findAllTaskAndRelative();
    Result<String> insertOneNewTask(Task task);
    Result<String> deleteById(Long id);
    Result<String> patchOneTask(Task task);
}
