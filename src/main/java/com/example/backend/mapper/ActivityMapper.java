package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.Activity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: 杨严
 * @Date: 2022/11/22/9:12 PM
 * @Description:
 */
/**
 * 继承BaseMapper可以省略xml的编写
 * */
@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {
}
