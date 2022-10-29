package com.backend.user.mapper;

import com.backend.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author hym
 * @Date 2022/10/29
 * @Description demo
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
