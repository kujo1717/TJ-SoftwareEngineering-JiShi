package com.example.backend.service;

import com.example.backend.entity.Tag;
import org.springframework.stereotype.Service;

@Service
public interface TagService {
    Tag findTag(Long tag_id);
}
