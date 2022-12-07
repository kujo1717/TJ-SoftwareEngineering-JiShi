package com.example.backend.controller;

import com.example.backend.common.Result;
import com.example.backend.entity.Activity;
import com.example.backend.entity.Poll;
import com.example.backend.entity.VoteOption;
import com.example.backend.service.PollService;
import com.example.backend.service.VoteOptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = {"VoteOption"})
@RestController
@RequestMapping("vote_option")
public class VoteOptionController {

  @Autowired
  private VoteOptionService voteOptionService;

  @ApiOperation("获取投票选项")
  @GetMapping("/getoptions/{poll_id}")
  public Result<List<VoteOption>> findVoteOption(@ApiParam(name = "poll_id", value = "投票id", required = true)
                                                 @PathVariable("poll_id") Long poll_id) {
    List<VoteOption> list;
    try {
      list = voteOptionService.findVoteOption(poll_id);
      return Result.success(list);
    } catch (Exception e) {
      return Result.fail(HttpStatus.EXPECTATION_FAILED.value(), "findPoll failed");
    }
  }
  @ApiOperation("统计总票数")
  @PutMapping("/putTotal/{option_id}")
  public int putTotal(@ApiParam(name="option_id", value="选项id", required = true)
                                     @PathVariable("option_id") Long option_id)
  {
    return voteOptionService.UpdateVotes(option_id);
  }
}
