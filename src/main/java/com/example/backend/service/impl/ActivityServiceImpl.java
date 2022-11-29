package com.example.backend.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.backend.common.Result;
import com.example.backend.dto.ActivityUserRole;
import com.example.backend.entity.Activity;
import com.example.backend.entity.ActivityApply;
import com.example.backend.mapper.ActivityApplyMapper;
import com.example.backend.mapper.ActivityMapper;
import com.example.backend.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public List<Map<String, Object>> getUserAllCreateActList(Long user_id, Short state) {
        /**mapper自定义SQL获取所有的活动*/
        List<Map<String,Object>> list=activityMapper.SelectCreateList(user_id);

        /**state传值非-1，则添加state filter*/
        list=list.stream().filter(act->{
            return state==-1?true:state.toString().equals(act.get("state").toString());
        }).collect(Collectors.toList());
        return list;
    }

    @Override
    public List<Map<String,Object>> getUserAllApplyActList(Long user_id,Short state) {
        /**mapper自定义SQL获取所有的活动*/
        List<Map<String,Object>> list=activityMapper.SelectApplyList(user_id);

        /**state传值非-1，则添加state filter*/
        list=list.stream().filter(act->{
                return state==-1?true:state.toString().equals(act.get("state").toString());
        }).collect(Collectors.toList());
        return list;
    }

    @Override
    @Transactional
    public List<Activity> getUserAllActList(Long creator_id){

        QueryWrapper<Activity> queryWrapper=new QueryWrapper<>();
        queryWrapper
                .eq("creator_id",creator_id);
        List<Activity> list=activityMapper.selectList(queryWrapper);
//        System.out.println(activityMapper.selectList(queryWrapper));

        return list;
    }


    @Override
    @Transactional
    public List<Activity> getTestActList() {
        QueryWrapper<Activity> queryWrapper=new QueryWrapper<>();
        queryWrapper
                .eq("summary","test");
        List<Activity> list=activityMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public Activity getAct(Long activity_id) {
        Activity act=activityMapper.selectById(activity_id);
        return act;
    }

    @Override
    @Transactional
    public Integer CreateAct(Activity act) {
        int newID = activityMapper.insert(act);

        return newID;
    }

    @Override
    public Integer DeleteAct(Long activity_id) {
        Integer delete_num;
        delete_num=activityMapper.deleteById(activity_id);
        //delete successful,delete one activity
        return delete_num;

    }

    @Override
    public Result<String> UpdateAct(Activity act){
        int resultCount = activityMapper.updateById(act);
        if(resultCount == 0)
            return Result.fail(500,"更新数据失败！");
        return Result.success("更新数据成功！");
    }

    @Override
    public ActivityUserRole isCreator(Long user_id, Long activity_id) {
        ActivityUserRole role=new ActivityUserRole();
        role.setActivity_id(activity_id);
        role.setUser_id(user_id);

        QueryWrapper<Activity> queryWrapper=new QueryWrapper<>();
        queryWrapper
                .eq("creator_id",user_id)
                .eq("activity_id",activity_id);
        Integer count=activityMapper.selectCount(queryWrapper);
        role.setIs_creator(count==1?true:false);
        return role;

    }


    @Override
    @Transactional
    public Integer SetActivityState(Long activity_id, Integer state_val) {
        UpdateWrapper<Activity> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("activity_id",activity_id).set("state",state_val);
        return activityMapper.update(null,updateWrapper);
    }

    @Override
    public List<Map<String, Object>> getUserAllParticipateActList(Long user_id,Short state) {
        List<Map<String,Object>> list=activityMapper.SelectParticipateList(user_id);
        list=list.stream().filter(act->{
            return state==-1?true:state.toString().equals(act.get("state").toString());
        }).collect(Collectors.toList());

        return list;
    }


}
