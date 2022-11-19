package com.example.backend.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author hym
 * @Date 2022/10/29
 * @Description demo
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
