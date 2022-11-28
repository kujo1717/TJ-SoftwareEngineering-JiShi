package com.example.backend.service;

import com.example.backend.common.Result;
import com.example.backend.dto.ActivityUserRole;
import com.example.backend.entity.Activity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 杨严
 * @Date: 2022/11/22/8:46 PM
 * @Description:
 */
@Service
public interface ActivityService {


    List<Activity> getAll();


    /**获取活动实体的列表，用户创建的*/
    List<Activity> getUserCreateActList(Long creator_id);

    /**获取活动实体的列表，测试用的*/
    List<Activity> getTestActList();

    /**返回一个活动*/
    Activity getAct(Long activity_id);


    /**新建活动*/
    Integer CreateAct(Activity act);

    /**删除活动*/
    Integer DeleteAct(Long activity_id);


    /**更新一个活动的信息,输入一个Activity，按照ID查找而更新*/
    Result<String> UpdateAct(Activity act);

    /**判断用户是不是活动的创建者*/
    ActivityUserRole isCreator(Long user_id, Long activity_id) ;


    /**修改活动的状态，指定为目标状态*/
    Integer SetActivityState(Long activity_id,Integer state_val);

}
