package com.example.backend.controller;

import com.alibaba.fastjson.JSON;
import com.example.backend.common.Result;
import com.example.backend.entity.*;
import com.example.backend.service.PollService;
import com.example.backend.service.VoteOptionService;
import com.mysql.jdbc.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Api(tags = {"Poll"})
@RestController
@RequestMapping("poll")

public class PollController {
  @Autowired
  private PollService pollService;
  @Autowired
  private VoteOptionService voteOptionService;

  @ApiOperation("新建投票")
  @PostMapping("/postPoll")
  public Result<String> insertOneNewPoll(@RequestBody Map<String,Object> pollWithOption)
  {

    DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String topic_text = JSON.toJSONString(pollWithOption.get("topic_text"));

    Long activity_id = Long.parseLong(JSON.toJSONString(pollWithOption.get("activity_id")));
    boolean multiple_choice=Boolean.parseBoolean(JSON.toJSONString(pollWithOption.get("multiple_choice")));
    List<options> voteoptions = JSON.parseArray(JSON.toJSONString(pollWithOption.get("option")), options.class);
    LocalDateTime deadline=LocalDateTime.now();
    if(JSON.toJSONString(pollWithOption.get("deadline")).length()>2) {
      deadline = LocalDateTime.parse(JSON.toJSONString(pollWithOption.get("deadline")),df);
    }
    Long poll_id=pollService.createPoll(activity_id,topic_text,deadline,multiple_choice);
    return voteOptionService.createVoteOptions(voteoptions,poll_id);
  }

  @ApiOperation("获取投票")
  @GetMapping("/getPoll/{activity_id}")
  public Result<List<Poll>> findPoll(@ApiParam(name="activity_id", value="活动id", required = true)
                               @PathVariable("activity_id") Long activity_id){
    List<Poll> list;
    try {
      list=pollService.findPoll(activity_id);
      return Result.success(list);
    }
    catch (Exception e){
      return Result.fail(HttpStatus.EXPECTATION_FAILED.value(),"findPoll failed");
    }
  }
}
