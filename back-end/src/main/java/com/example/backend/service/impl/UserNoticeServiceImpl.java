package com.example.backend.service.impl;

import com.example.backend.entity.UserNotice;
import com.example.backend.mapper.UserNoticeMapper;
import com.example.backend.service.UserNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/11/28
 * @JDKVersion 17.0.4
 */
@Service
public class UserNoticeServiceImpl implements UserNoticeService {
    @Autowired
    UserNoticeMapper userNoticeMapper;

    @Override
    public List<UserNotice> findOneUserAllNotice(Long userId) {
        return userNoticeMapper.selectOneUserAllNotice(userId);
    }

    @Override
    public int modifyNoticeStatus(Long userId, Long noticeId) {
        return userNoticeMapper.updateNoticeStatus(userId, noticeId);
    }
}
