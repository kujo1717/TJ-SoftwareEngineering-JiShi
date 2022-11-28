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

import java.util.Date;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/11/24
 * @JDKVersion 17.0.4
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("message")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {

    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long messageId;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long senderId;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long receiverId;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long msgInfoId;

    private int status;

    private Date createTime;
}
