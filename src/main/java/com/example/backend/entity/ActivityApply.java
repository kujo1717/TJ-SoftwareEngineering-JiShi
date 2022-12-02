package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: 杨严
 * @Date: 2022/11/26/1:13 PM
 * @Description: 活动报名的联系表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("activity_apply")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActivityApply {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long activity_id;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long user_id;

    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd hh:mm:ss")
    private Date apply_time;
}
