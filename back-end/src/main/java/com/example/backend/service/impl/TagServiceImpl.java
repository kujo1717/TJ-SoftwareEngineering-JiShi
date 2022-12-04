package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.entity.Tag;
import com.example.backend.entity.VoteOption;
import com.example.backend.mapper.TagMapper;
import com.example.backend.service.ActivityTagService;
import com.example.backend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;
    public Tag findTag(Long tag_id){
        Tag tag=tagMapper.selectById(tag_id);
        System.out.println(tag);
        return tag;
    }
}
