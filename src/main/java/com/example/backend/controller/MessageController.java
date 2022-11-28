package com.example.backend.controller;

import com.example.backend.Dto.SystemNotice;
import com.example.backend.common.Result;
import com.example.backend.entity.Message;
import com.example.backend.entity.MessageInfo;
import com.example.backend.service.MessageInfoService;
import com.example.backend.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/11/24
 * @JDKVersion 17.0.4
 */
@Api(tags = {"Message"})
@RestController
@RequestMapping("message")
public class MessageController {
    @Autowired
    MessageService messageService;

    @Autowired
    MessageInfoService messageInfoService;

    @ApiOperation("获取该用户的所有的消息")
    @GetMapping("getOneUserAllMessage")
    public Result<List<SystemNotice>> findOneUserAllClassificationTitle(@ApiParam(name = "userId", value = "要查找的用户id", required = true)
                                                                   @RequestParam("userId") Long userId) {
        try {
            List<SystemNotice> systemMessages = new ArrayList<>();

            List<Message> messageList = messageService.findOneUserAllClassificationTitle(userId);
//            for (Message message : messageList) {
//                MessageInfo messageInfo = messageInfoService.findMessageInfo(message.getMsgInfoId());
//                SystemNotice systemMessage = new SystemNotice(messageInfo.getTitle(), messageInfo.getContent(), message.getCreateTime(), message.getStatus());
//                systemMessages.add(systemMessage);
//            }
            return Result.success(systemMessages);
        } catch (Exception e) {
            return Result.fail(500, e.getMessage());
        }

    }
}
