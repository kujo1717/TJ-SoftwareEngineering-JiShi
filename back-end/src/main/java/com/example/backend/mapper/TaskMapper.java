package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.Task;
import org.apache.ibatis.annotations.*;

import java.io.Serializable;
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


    @Select("SELECT * FROM task LEFT OUTER JOIN relativetask using(task_id) WHERE task_id=${id}")
    @Results(
            {
                    //column为数据库字段名，property为实体类字段名
                    @Result(column = "task_id",property = "taskId"),
                    @Result(column = "task_title",property = "taskTitle"),
                    @Result(column = "task_detail",property = "taskDetail"),
                    @Result(column = "task_state",property = "taskState"),
                    @Result(column = "classification_title",property = "classificationTitle"),
                    @Result(column = "priority",property = "priority"),
                    @Result(column = "start_time",property = "startTime"),
                    @Result(column = "end_time",property = "endTime"),
                    @Result(column = "is_parent",property = "isParent"),
                    @Result(column = "relative_task_id",property = "relativeTask",javaType = List.class,
                            one=@One(select = "com.example.backend.mapper.TaskMapper.selectById")
                    )
            }
    )
    List<Task> selectOneTaskAndRelative(Serializable id);

    @Select("SELECT * FROM task LEFT OUTER JOIN relativetask using(task_id) WHERE user_id=${userId}")
    @Results(
            {
                    //column为数据库字段名，property为实体类字段名
                    @Result(column = "task_id",property = "taskId"),
                    @Result(column = "task_title",property = "taskTitle"),
                    @Result(column = "task_detail",property = "taskDetail"),
                    @Result(column = "task_state",property = "taskState"),
                    @Result(column = "classification_title",property = "classificationTitle"),
                    @Result(column = "priority",property = "priority"),
                    @Result(column = "start_time",property = "startTime"),
                    @Result(column = "end_time",property = "endTime"),
                    @Result(column = "is_parent",property = "isParent"),
                    @Result(column = "relative_task_id",property = "relativeTask",javaType = List.class,
                            one=@One(select = "com.example.backend.mapper.TaskMapper.selectById")
                    )
            }
    )
    List<Task> selectAllTaskAndRelative(@Param("userId") Long userId);

    @Select("SELECT * FROM task WHERE user_id=${userId} AND classification_title='${classificationTitle}'")
    List<Task> selectOneUserOneSortAllTask(@Param("userId") Long userId, @Param("classificationTitle") String classificationTitle);

}
