package com.example.backend.service.impl;

import com.example.backend.common.Result;
import com.example.backend.entity.Classification;
import com.example.backend.mapper.ClassificationMapper;
import com.example.backend.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author hym
 * @Date $ $
 * @MethodName $
 * @Description $
 * @Return $
 * @Throw $
 */
@Service
public class ClassificationServiceImpl implements ClassificationService {
    @Autowired
    ClassificationMapper classificationMapper;

    @Override
    public List<Classification> findOneUserAllClassificationTitle(Long userId) {
        List<Classification> classificationList = classificationMapper.selectOneUserAllClassification(userId);
        return classificationList;
    }

    @Override
    public int insertOneClassification(Long userId, String classificationTitle) {
        //Classification newClass = new Classification(classificationTitle, userId);
        int res = classificationMapper.insertOneClassification(userId, classificationTitle);
        return res;
    }
}
