package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("vote_option")
public class VoteOption {
  @TableId(type = IdType.ASSIGN_ID)
  private Long option_id;
  private String option_name;
  private Long poll_id;
  private int vote_num;

  public String getOption_name() {
    return option_name;
  }
}
