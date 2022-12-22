package com.example.backend.controller;

import com.example.backend.dto.ActivityNotice;
import com.example.backend.dto.FriendRequestDto;
import com.example.backend.dto.SystemNotice;
import com.example.backend.common.Result;
import com.example.backend.entity.ItemNotice;
import com.example.backend.entity.Notice;
import com.example.backend.entity.UserNotice;
import com.example.backend.entity.friendRequest;
import com.example.backend.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.spi.DirStateFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/11/28
 * @JDKVersion 17.0.4
 */
@Api(tags = {"Notice"})
@RestController
@RequestMapping("notice")
public class NoticeController {
    @Autowired
    UserNoticeService userNoticeService;

    @Autowired
    NoticeService noticeService;

    @Autowired
    ItemNoticeService itemNoticeService;

    @Autowired
    FriendRequestService friendRequestService;
    @Autowired
    FriendService friendService;
    @ApiOperation("获取该用户所有系统通知信息")
    @GetMapping("getSystemNoticeByUserID")
    public Result<List<SystemNotice>> findOneUserSystemNoticeInfo(@ApiParam(name = "userId", value = "要查找的用户名", required = true)
                                                                  @RequestParam("userId") Long userId) {
        try {
            ArrayList<SystemNotice> systemNotices = new ArrayList<SystemNotice>();
            List<UserNotice> oneUserAllNotice = userNoticeService.findOneUserAllNotice(userId);
            for (UserNotice un : oneUserAllNotice) {
                Notice noticeInfo = noticeService.getNoticeById(un.getNoticeId());
                if (noticeInfo.getType() == 1) {
                    SystemNotice systemNotice = new SystemNotice(noticeInfo.getNoticeId(), noticeInfo.getTitle(), noticeInfo.getContent(), noticeInfo.getCreateTime(), un.getStatus());
                    systemNotices.add(systemNotice);
                }
            }
            return Result.success(systemNotices);

        } catch (Exception e) {
            return Result.fail(500, e.getMessage());
        }
    }
    //好友通知
    @ApiOperation("获取该用户所有好友申请")
    @GetMapping("getFriendRequest")
    public Result<List<FriendRequestDto>> findOneUserFriendRequest(@ApiParam(name="userId",value = "要查找的用户名",required = true)
                                                                @RequestParam("userId") Long userId){
        try{
            var re=friendRequestService.findFriendRequest(userId);
            return Result.success(re);
        }catch (Exception e){
            return Result.fail(500,e.getMessage());
        }
    }
    //结束

    @ApiOperation("获取该用户所有活动通知")
    @GetMapping("getActivityNoticeByUserID")
    public Result<List<ActivityNotice>> findOneUserActivityNoticeInfo(@ApiParam(name = "userId", value = "要查找的用户名", required = true)
                                                                      @RequestParam("userId") Long userId) {
        try {
            ArrayList<ActivityNotice> activityNotices = new ArrayList<ActivityNotice>();
            List<UserNotice> oneUserAllNotice = userNoticeService.findOneUserAllNotice(userId);
            for (UserNotice un : oneUserAllNotice) {
                Notice noticeInfo = noticeService.getNoticeById(un.getNoticeId());
                if (noticeInfo.getType() == 2) {
                    ActivityNotice activityNotice = new ActivityNotice(noticeInfo.getNoticeId(), noticeInfo.getActivityId(), noticeInfo.getTitle(), noticeInfo.getContent(), noticeInfo.getCreateTime(), un.getStatus());
                    activityNotices.add(activityNotice);
                }
            }
            return Result.success(activityNotices);

        } catch (Exception e) {
            return Result.fail(500, e.getMessage());
        }
    }

    @ApiOperation("获取该用户所有个人事项通知")
    @GetMapping("getItemNoticeByUserID")
    public Result<List<ItemNotice>> findItemNotice(@ApiParam(name = "userId", value = "用户ID", required = true)
                                                   @RequestParam("userId") Long userId) {
        try {
            List<ItemNotice> itemNotice = itemNoticeService.findItemNotice(userId);
            return Result.success(itemNotice);

        } catch (Exception e) {
            return Result.fail(500, e.getMessage());
        }
    }


    @ApiOperation("更新通知的已读状态")
    @PatchMapping("modifyNoticeStatus")
    public Result<String> modifyNoticeStatus(@ApiParam(name = "userId", value = "用户ID", required = true)
                                             @RequestParam("userId") Long userId,
                                             @ApiParam(name = "noticeId", value = "通知ID", required = true)
                                             @RequestParam("noticeId") Long noticeId) {
        try {
            int res = userNoticeService.modifyNoticeStatus(userId, noticeId);
            if (res == 1) {
                return Result.success("消息状态更新成功！");
            } else {
                return Result.success("消息状态更新失败！");
            }
        } catch (Exception e) {
            return Result.fail(500, e.getMessage());
        }
    }

    @ApiOperation("更新个人事项通知的已读状态")
    @PatchMapping("modifyItemNoticeStatus")
    public Result<String> modifyItemNoticeStatus(@ApiParam(name = "itemNoticeId", value = "事项通知ID", required = true)
                                                 @RequestParam("itemNoticeId") Long itemNoticeId) {
        try {
            int res = itemNoticeService.modifyItemNoticeStatus(itemNoticeId);
            if (res == 1) {
                return Result.success("消息状态更新成功！");
            } else {
                return Result.success("消息状态更新失败！");
            }
        } catch (Exception e) {
            return Result.fail(500, e.getMessage());
        }
    }
    @ApiOperation("拒绝好友申请")
    @PatchMapping("rejectFriendRequest")
    public Result<String> rejectFriendRequest(@ApiParam(name = "userid", value = "好友通知所属人id", required = true)
                                                        @RequestParam("userid") Long userid,
                                                    @ApiParam(name = "friendid", value = "对方id", required = true)
                                                    @RequestParam("friendid") Long friendid){
        try{
            if (friendRequestService.modifyFriendRequestStatus(userid,friendid)){
                friendRequestService.sendFriendRequestById(friendid,userid,null,2);
                return Result.success("信息状态更新成功");
            }
            return Result.success("更新失败");
        }catch (Exception e){
            return Result.fail(500, e.getMessage());
        }
    }
    @ApiOperation("同意好友申请")
    @PatchMapping("acceptFriendRequest")
    public Result<String> acceptFriendRequest(@ApiParam(name = "userid", value = "好友通知所属人id", required = true)
                                                  @RequestParam("userid") Long userid,
                                              @ApiParam(name = "friendid", value = "对方id", required = true)
                                                  @RequestParam("friendid") Long friendid){
        try{
            if (friendRequestService.modifyFriendRequestStatus(userid,friendid)){
                friendRequestService.sendFriendRequestById(friendid,userid,null,3);
                friendService.addFriend(userid,friendid);
                return Result.success("信息状态更新成功");
            }
            return Result.success("更新失败");
        }catch (Exception e){
            return Result.fail(500, e.getMessage());
        }
    }
}
