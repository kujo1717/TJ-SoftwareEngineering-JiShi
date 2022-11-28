package com.example.backend.service.impl;

import com.example.backend.entity.Group;
import com.example.backend.entity.Message;
import com.example.backend.mapper.GroupMapper;
import com.example.backend.mapper.MessageMapper;
import com.example.backend.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/11/25
 * @JDKVersion 17.0.4
 */
@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    GroupMapper groupMapper;

    @Override
    public List<Group> findOneGroupAllUser(Long groupId) {
        List<Group> groupList = groupMapper.selectOneGroupAllUser(groupId);
        return groupList;
    }
}
