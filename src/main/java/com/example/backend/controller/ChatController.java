package com.example.backend.controller;

import com.example.backend.dto.FormatMessage;
import com.example.backend.dto.FormatUser;
import com.example.backend.common.Result;
import com.example.backend.entity.File;
import com.example.backend.entity.Message;
import com.example.backend.entity.User;
import com.example.backend.mapper.MessageMapper;
import com.example.backend.service.FileService;
import com.example.backend.service.MessageService;
import com.example.backend.service.UserService;
import com.example.backend.service.WebSocket;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/12/3
 * @JDKVersion 17.0.4
 */
@Api(tags = {"Chat"})
@RestController
@RequestMapping("chat")
public class ChatController {

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @Autowired
    FileService fileService;

    @Autowired
    WebSocket webSocket;

    private String formatStatus(int status) {
        if (status == 0) {
            return "going";
        } else if (status == 1) {
            return "succeed";
        } else if (status == 2) {
            return "failed";
        }
        return null;
    }

    private String formatType(int status) {
        if (status == 0) {
            return "text";
        } else if (status == 1) {
            return "image";
        } else if (status == 2) {
            return "file";
        }
        return null;
    }

    private FormatUser formatUser(User user) {
        return new FormatUser(user.getId(), user.getName(), user.getAvatar());
    }

    private FormatMessage formatMessage(Message m){
        // 获取发送消息的用户的信息
        User user = userService.findUser(m.getSenderId());
        FormatUser formatUser = formatUser(user);
        // 格式化信息的状态和类型
        String status = formatStatus(m.getStatus());
        String type = formatType(m.getType());
        if (Objects.equals(type, "file")) {
            // 是文件消息
            File file = fileService.getFileInfoById(m.getFileId());
            FormatMessage formatMessage = new FormatMessage(m.getMessageId(), status, type, m.getSendTime(), m.getContent(), file.getFileSize(), file.getFileName(), formatUser, m.getActivityId().toString());
            return formatMessage;
        } else {
            // 不是文件消息
            FormatMessage formatMessage = new FormatMessage(m.getMessageId(), status, type, m.getSendTime(), m.getContent(), formatUser, m.getActivityId().toString());
            return formatMessage;
        }
    }

    @ApiOperation("获取某个活动的所有消息")
    @GetMapping("getActivityAllMessage")
    public Result<List<FormatMessage>> findActivityAllMessage(@ApiParam(name = "activityId", value = "要查找的活动id", required = true)
                                                              @RequestParam("activityId") Long activityId) {
        try {
            ArrayList<FormatMessage> formatMessages = new ArrayList<>();
            List<Message> activityAllMessages = messageService.findActivityAllMessage(activityId);
            for (Message m : activityAllMessages) {
                FormatMessage formatMessage = formatMessage(m);
                formatMessages.add(formatMessage);
            }
            return Result.success(formatMessages);
        } catch (Exception e) {
            return Result.fail(500, e.getMessage());
        }

    }

    @ApiOperation("添加消息")
    @PostMapping("addMessage")
    public Result<String> addMessage(@ApiParam(name = "activityId", value = "活动ID", required = true)
                                     @RequestParam() Long activityId,
                                     @ApiParam(name = "senderId", value = "发送者ID", required = true)
                                     @RequestParam() Long senderId,
                                     @ApiParam(name = "status", value = "消息状态", required = true)
                                     @RequestParam() int status,
                                     @ApiParam(name = "type", value = "消息类型", required = true)
                                     @RequestParam() int type,
                                     @ApiParam(name = "sendTime", value = "发送时间", required = true)
                                     @RequestParam() Long sendTime,
                                     @ApiParam(name = "content", value = "消息内容")
                                     @RequestParam() String content) {
        try {
            Message message = new Message(activityId, senderId, status, type, sendTime, content);
            System.out.println(message);
            messageService.addMessage(message);

            FormatMessage formatMessage = formatMessage(message);
            webSocket.sendMessage(formatMessage);
            return Result.success("yes");
        } catch (Exception e) {
            return Result.fail(500,e.getMessage());
        }

    }

}
