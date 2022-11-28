package com.example.backend.dto;

import com.example.backend.entity.UserMark;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @Author: 杨严
 * @Date: 2022/11/27/10:10 PM
 * @Description: 用户受到的评分
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserMarkedListDto {
    List<UserMark> userMarkList;
}
