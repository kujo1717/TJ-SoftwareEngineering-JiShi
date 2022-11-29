package com.example.backend.service;

import com.example.backend.common.Result;
import com.example.backend.dto.ActivityBriefDto;
import com.example.backend.entity.Activity;
import com.example.backend.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: 杨严
 * @Date: 2022/11/22/8:46 PM
 * @Description:
 */
@Service
public interface ActivityService {


    List<Activity> getAll();


    /**获取用户的所有活动*/
    List<Activity> getUserCreateActList(Long creator_id);

    /**返回一个活动*/
    Activity getAct(Long activity_id);


    /**新建活动*/
    Result<String> createAct(Activity act);

    /**更新一个活动的信息,输入一个Activity，按照ID查找而更新*/
    Result<String> UpdateAct(Activity act);


}
