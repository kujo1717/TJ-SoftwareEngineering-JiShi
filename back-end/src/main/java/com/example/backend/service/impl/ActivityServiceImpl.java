package com.example.backend.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.common.Result;
import com.example.backend.dto.ActivityBriefDto;
import com.example.backend.entity.Activity;
import com.example.backend.mapper.ActivityMapper;
import com.example.backend.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: 杨严
 * @Date: 2022/11/22/8:47 PM
 * @Description:
 */
@Service

public class ActivityServiceImpl implements ActivityService{

    @Autowired
    ActivityMapper activityMapper;

    @Override
    public List<Activity> getAll(){
        QueryWrapper<Activity> queryWrapper=new QueryWrapper<>();

        List<Activity> list=activityMapper.selectList(queryWrapper);
        System.out.println(activityMapper.selectList(queryWrapper));

        return list;
    }
    @Override
    @Transactional
    public List<Activity> getUserCreateActList(Long creator_id){

        QueryWrapper<Activity> queryWrapper=new QueryWrapper<>();
        queryWrapper
                .eq("creator_id",creator_id);
        List<Activity> list=activityMapper.selectList(queryWrapper);
        System.out.println(activityMapper.selectList(queryWrapper));

        return list;
    }

    @Override
    public Activity getAct(Long activity_id) {
        Activity act=activityMapper.selectById(activity_id);
        return act;
    }

    @Override
    @Transactional
    public Result<String> createAct(Activity act) {
        int newID = activityMapper.insert(act);
        if(newID == Integer.MIN_VALUE + 1001) {
            return Result.fail(500,"插入数据失败！");
        }
        return Result.success("插入数据成功！");
    }



    @Override

    public Result<String> UpdateAct(Activity act){
        int resultCount = activityMapper.updateById(act);
        if(resultCount == 0)
            return Result.fail(500,"更新数据失败！");
        return Result.success("更新数据成功！");
    }
}
