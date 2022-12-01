package com.example.backend.service.impl;

import com.example.backend.entity.ActivityTag;
import com.example.backend.entity.Tag;
import com.example.backend.mapper.ActivityTagMapper;
import com.example.backend.service.ActivityTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: 杨严
 * @Date: 2022/12/01/7:21 PM
 * @Description:
 */
@Service
public class ActivityTagServiceImpl implements ActivityTagService {
    @Autowired
    ActivityTagMapper activityTagMapper;
    @Override
    public Integer AddActTags(Long activity_id, List<Long> tagList) {
        for (Long tag_id:tagList){
            activityTagMapper.insert(new ActivityTag(activity_id,tag_id));
        }
        return null;
    }

    @Override
    public Integer DeleteActAllTags(Long activity_id) {
        return activityTagMapper.DeleteActAllTags(activity_id);
    }

    @Override
    public List<Map<String, Object>> GetActAllTags(Long activity_id) {
        return activityTagMapper.SelectActAllTags(activity_id);
    }
}
