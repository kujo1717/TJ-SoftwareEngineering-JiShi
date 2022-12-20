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

import java.io.File;

/**
 * @Author hym
 * @Date 2022/10/29
 * @Description demo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @JsonSerialize(using= ToStringSerializer.class)
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private String password;
    private String email;
    private int age;
    private String introduce;
    private String avatar;
    private String role;
}
