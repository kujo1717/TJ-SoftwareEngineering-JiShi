package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author hym
 * @Date 2022/11/19
 * @Description 事项mapper
 */
@Mapper
public interface TaskMapper extends BaseMapper<Task> {
    //查询一个月内的所有事项
    @Select("SELECT * FROM task WHERE end_time BETWEEN timestamp('${year}-${month}-01') AND (timestamp(date_add(timestamp('${year}-${month}-01'), interval - day('${year}-${month}-01') + 30 day))) OR start_time BETWEEN timestamp('${year}-${month}-01') AND (timestamp(date_add(timestamp('${year}-${month}-01'), interval - day('${year}-${month}-01') + 30 day)))")
    List<Task> selectByMonth(@Param("year") int year, @Param("month") int month);
}
