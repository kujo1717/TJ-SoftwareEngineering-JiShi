package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 杨严
 * @Date: 2022/12/13/8:50 AM
 * @Description: 举报单的表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "report")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Report {
    @JsonSerialize(using= ToStringSerializer.class)
    @TableId(type = IdType.ASSIGN_ID)
    private Long reportId;
    private String type;
    private String detail;
    private Long activityId;
    private Long userId;
    private Long informerId;
    private String state;
    private String image;
    private String targetType;
}
