package com.example.backend.mapper;

import com.example.backend.entity.Group;
import com.example.backend.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/11/25
 * @JDKVersion 17.0.4
 */
@Mapper
public interface GroupMapper {
    @Select("SELECT * FROM `group` WHERE group_id=${groupId}")
    List<Group> selectOneGroupAllUser(@Param("groupId") Long groupId);
}
