package com.example.backend.service;

import com.example.backend.entity.Group;
import com.example.backend.entity.MessageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/11/25
 * @JDKVersion 17.0.4
 */
@Service
public interface GroupService {
    List<Group> findOneGroupAllUser(Long groupId);
}
