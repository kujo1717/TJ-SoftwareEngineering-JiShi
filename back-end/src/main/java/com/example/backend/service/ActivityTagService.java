package com.example.backend.service;

import com.example.backend.entity.ActivityTag;
import com.example.backend.entity.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ActivityTagService {
    List<ActivityTag> getActTags(Long activity_id);
    List<Long> getActIDByTags(List<Long> requiredTags);
    List<Long> getActIDByTag(Long requiredTagID);
}
