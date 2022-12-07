package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.User;
import com.example.backend.entity.VoteOption;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;

@Mapper
public interface VoteOptionMapper extends BaseMapper<VoteOption> {
  @Update("UPDATE vote_option SET vote_num='${vote_num}+1' WHERE option_id = '${option_id}'")
  int updateVotes(@Param("option_id") Long option_id);
}
