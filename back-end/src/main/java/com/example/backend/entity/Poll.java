package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("poll")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Poll{
  @JsonSerialize(using= ToStringSerializer.class)
  @TableId(type = IdType.ASSIGN_ID)
  private Long poll_id;
  private Long activity_id;
  private String topic_text;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private LocalDateTime deadline;
  private boolean multiple_choice;

  public void setActivity_id(Long activity_id) {
    this.activity_id = activity_id;
  }

  public void setDeadline(LocalDateTime deadline) {
    this.deadline = deadline;
  }

  public void setMultiple_choice(boolean multiple_choice) {
    this.multiple_choice = multiple_choice;
  }

  public void setTopic_text(String topic_text) {
    this.topic_text = topic_text;
  }
}
