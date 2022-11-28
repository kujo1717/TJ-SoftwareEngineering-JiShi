package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.Classification;
import com.example.backend.entity.Task;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClassificationMapper extends BaseMapper<Task> {
    @Select("SELECT * FROM classification WHERE user_id=${userId}")
    List<Classification> selectOneUserAllClassification(@Param("userId") Long userId);

    @Insert("INSERT INTO classification(user_id,classification_title) VALUES(${userId},'${classificationTitle}')")
    int insertOneClassification(@Param("userId") Long userId, @Param("classificationTitle") String classificationTitle);
}
