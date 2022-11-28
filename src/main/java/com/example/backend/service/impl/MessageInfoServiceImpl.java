package com.example.backend.service.impl;

import com.example.backend.entity.Message;
import com.example.backend.entity.MessageInfo;
import com.example.backend.mapper.MessageInfoMapper;
import com.example.backend.service.MessageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/11/24
 * @JDKVersion 17.0.4
 */
@Service
public class MessageInfoServiceImpl implements MessageInfoService {
    @Autowired
    MessageInfoMapper messageInfoMapper;

    @Override
    public MessageInfo findMessageInfo(Long msgInfoId) {
        MessageInfo messageInfo = messageInfoMapper.selectMessageInfo(msgInfoId);
        return messageInfo;
    }
}
