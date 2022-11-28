package com.example.backend.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.Group;
import com.example.backend.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author hym
 * @Date 2022/10/29
 * @Description demo
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user WHERE user_id=${userId}")
    User selectUserById(@Param("userId") Long userId);
}
