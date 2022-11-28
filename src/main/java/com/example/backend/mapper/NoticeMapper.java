package com.example.backend.mapper;

import com.example.backend.entity.Classification;
import com.example.backend.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/11/28
 * @JDKVersion 17.0.4
 */
@Mapper
public interface NoticeMapper {
    @Select("SELECT * FROM notice WHERE notice_id=${noticeId}")
    Notice selectNoticeById(@Param("noticeId") Long noticeId);
}
