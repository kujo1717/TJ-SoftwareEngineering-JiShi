package com.example.backend.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author hym
 * @Date $ $
 * @MethodName $
 * @Description $
 * @Return $
 * @Throw $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("task")
public class Task {
    @TableId(type = IdType.ASSIGN_ID)
    private Long taskId;
    private String taskTitle;
    private String taskDetail;
    private short taskState;
    private String classificationTitle;
    private short priority;
    private Timestamp startTime;
    private Timestamp endTime;
    private short isParent;

    @TableField(exist = false)
    private List<Task> relativeTask;

    public void addOneRelativeTask(Task task)
    {
        relativeTask.add(task);
    }
}
