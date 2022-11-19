package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.Task;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author hym
 * @Date 2022/11/19
 * @Description 事项mapper
 */
@Mapper
public interface TaskMapper extends BaseMapper<Task> {
}
