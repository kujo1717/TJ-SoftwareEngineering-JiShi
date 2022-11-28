package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.entity.ActivityApply;
import com.example.backend.entity.ActivityParticipate;
import com.example.backend.mapper.ActivityParticipateMapper;
import com.example.backend.service.ActivityApplyService;
import com.example.backend.service.ActivityParticipateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.Map;

/**
 * @Author: 杨严
 * @Date: 2022/11/27/9:02 AM
 * @Description:
 */
@Service
public class ActivityParticipateServiceImpl implements ActivityParticipateService {
    @Autowired
    ActivityParticipateMapper activityParticipateMapper;

    @Override
    public Integer AddParticipant(ActivityParticipate participate) {
        Integer newID=activityParticipateMapper.insert(participate);
        return newID;
    }

    @Override
    public Integer RemoveParticipant(ActivityParticipate participate) {
        Integer res;
        QueryWrapper<ActivityParticipate> queryWrapper=new QueryWrapper<>();
        queryWrapper
                .eq("activity_id",participate.getActivity_id())
                .eq("user_id",participate.getUser_id());
        res=activityParticipateMapper.delete(queryWrapper);
        return res;
    }

    @Override
    public Integer CountParticipantNum(Long activity_id) {
        Integer num=0;
        QueryWrapper<ActivityParticipate> queryWrapper=new QueryWrapper<>();
        queryWrapper
                .eq("activity_id",activity_id);
        num=activityParticipateMapper.selectCount(queryWrapper);

        return num;
    }

    @Override
    public Integer DeleteAct(Long activity_id) {
        Integer delete_num=activityParticipateMapper.deleteById(activity_id);
        return delete_num;
    }
}
