package com.example.backend.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

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
    Long taskId;
    String taskTitle;
    String taskDetail;
    short taskState;
    String classificationTitle;
    short priority;
    Timestamp startTime;
    Timestamp endTime;
    short isParent;
}
