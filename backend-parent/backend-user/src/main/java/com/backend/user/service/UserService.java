package com.backend.user.service;

import com.backend.common.Result;
import com.backend.common.user.UserBo;

/**
 * @Author hym
 * @Date 2022/10/29
 * @Description demo
 */
public interface UserService {
    Result<UserBo> findUser(Long id);
}
