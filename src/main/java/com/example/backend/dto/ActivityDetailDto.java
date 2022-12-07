package com.example.backend.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.backend.entity.Activity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author: 杨严
 * @Date: 2022/11/26/8:21 AM
 * @Description:
 */
@Data

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActivityDetailDto {
    @JsonSerialize(using= ToStringSerializer.class)
    @TableId(type = IdType.ASSIGN_ID)
    private Long activity_id;
    private String title_name;
    private String detail_text;
    private String summary;


    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd hh:mm:ss")
    private Date start_time;

    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd hh:mm:ss")
    private Date end_time;

    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd hh:mm:ss")
    private Date create_time;

    private String address;
    private String city;
    private Integer hit_num;

    private Boolean limit_capacity;
    private Integer capacity;

    private Integer current_people;

    private Short status;
    private Short repeat_interval;
    private Long creator_id;


    /**组装dto*/
    public ActivityDetailDto(Activity activity){
        /**activity实体*/
        this.activity_id=activity.getActivity_id();
        this.title_name=activity.getTitle_name();
        this.detail_text=activity.getDetail_text();
        this.summary=activity.getSummary();
        this.start_time=activity.getStart_time();
        this.end_time=activity.getEnd_time();
        this.create_time=activity.getCreate_time();
        this.address=activity.getAddress();
        this.city=activity.getCity();
        this.hit_num=activity.getHit_num();
        this.limit_capacity=activity.getLimit_capacity();
        this.capacity=activity.getCapacity();
        this.current_people=activity.getCurrent_people();
        this.status=activity.getStatus();
        this.creator_id=activity.getCreator_id();

    }

}
