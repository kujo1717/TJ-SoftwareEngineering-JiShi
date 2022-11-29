package com.example.backend.service;

import com.example.backend.entity.Activity;
import com.example.backend.entity.ActivityParticipate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 杨严
 * @Date: 2022/11/27/9:02 AM
 * @Description:
 */
@Service
public interface ActivityParticipateService {
    /**
     * 用户参加某活动
     */
    Integer AddParticipant(ActivityParticipate participate);

    /**
     * 用户退出活动的参与
     * */
    Integer RemoveParticipant(ActivityParticipate participate);

    /**
     * 活动的参与人数
     * */
    Integer CountParticipantNum(Long activity_id);

    /**
     * 删除某活动的所有记录
     * */
    Integer DeleteAct(Long activity_id);



    /**获取用户的所有参与*/
//    List<Activity> getUserParticipateList(Long creator_id);
}
