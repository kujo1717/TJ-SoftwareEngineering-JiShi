package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("friend_request")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class friendRequest {
    private Long userid;
    private Long friendid;
    private Date createTime;
    private int status;
}
