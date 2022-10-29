package com.backend.user.service.impl;

import com.backend.common.Result;
import com.backend.common.user.UserBo;
import com.backend.user.entity.User;
import com.backend.user.mapper.UserMapper;
import com.backend.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author hym
 * @Date 2022/10/29
 * @Description demo
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result<UserBo> findUser(Long id){
        User user = userMapper.selectById(id);//利用mybatis-plus的单表查询，自己不用写SQL语句
        //没有找到用户，返回错误码
        if(user == null){
            return Result.fail(10001,"用户不存在");
        }
        UserBo userVo = new UserBo();
        BeanUtils.copyProperties(user, userVo);//这个函数是把user对象的内容拷贝到userVo对象中

        //找到用户，返回正确信息
        return Result.success(userVo);
    }
}
