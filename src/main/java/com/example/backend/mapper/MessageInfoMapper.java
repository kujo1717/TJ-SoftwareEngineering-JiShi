package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.Message;
import com.example.backend.entity.MessageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/11/24
 * @JDKVersion 17.0.4
 */
@Mapper
public interface MessageInfoMapper extends BaseMapper<MessageInfo> {
    @Select("SELECT * FROM message_info WHERE msg_info_id=${msg_info_id}")
    MessageInfo selectMessageInfo(@Param("msg_info_id") Long msgInfoId);
}
