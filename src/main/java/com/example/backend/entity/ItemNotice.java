package com.example.backend.entity;

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
 * @Date 2022/11/28
 * @JDKVersion 17.0.4
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("item_notice")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemNotice {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long itemNoticeId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    private String title;
    private String content;
    private Date createTime;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long itemId;
    private int status;
}
