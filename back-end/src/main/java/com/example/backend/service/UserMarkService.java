package com.example.backend.service;

import com.example.backend.entity.UserMark;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 杨严
 * @Date: 2022/11/27/9:56 PM
 * @Description:
 */
@Service
public interface UserMarkService {

    /**添加 一条用户间评价*/
    Integer AddUserMark(UserMark userMark);

    /**获取某用户的所有被评价*/
    List<UserMark> GetUserAllMarked(Long user_id);

}
