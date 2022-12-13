package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.backend.entity.Report;
import com.example.backend.mapper.ReportMapper;
import com.example.backend.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 杨严
 * @Date: 2022/12/13/9:23 AM
 * @Description:
 */
@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportMapper reportMapper;

    @Override
    public Map<String, Object> getReportList(String state, String target_type) {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Report> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq(state != null, "state", state)
                .eq(target_type != null, "target_type", target_type);
        List<Report> reports = reportMapper
                .selectList(queryWrapper);
        map.put("reports", reports);
        return map;
    }

    @Override
    public Map<String, Object> addReport(Report report) {
        Map<String, Object> map = new HashMap<>();
        Integer i = reportMapper.insert(report);
        map.put("i", i);
        map.put("report", report);
        return map;
    }

    @Override
    public Map<String, Object> changeReportState(String state, Long report_id) {
        Map<String, Object> map = new HashMap<>();
        Integer i = reportMapper.update(
                null,
                Wrappers.<Report>lambdaUpdate()
                        .eq(Report::getReportId, report_id)
                        .set(Report::getState, state)
        );
        map.put("i", i);
        map.put("state", state);
        return map;
    }

    @Override
    public Map<String, Object> getReport(Long report_id) {
        Map<String, Object> map = new HashMap<>();
        Report report = reportMapper.selectById(report_id);
        map.put("report", report);
        return map;
    }


    @Override
    public Map<String, Object> getReportListNumByState(String state) {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Report> queryWrapper_act = new QueryWrapper<>();
        QueryWrapper<Report> queryWrapper_user = new QueryWrapper<>();
        queryWrapper_act
                .eq("target_type", "0")
                .eq(state != null, "state", state);
        queryWrapper_user
                .eq("target_type", "1")
                .eq(state != null, "state", state);

        List<Report> act_list = reportMapper.selectList(queryWrapper_act);
        List<Report> user_list = reportMapper.selectList(queryWrapper_user);
        map.put("activity",act_list.size());
        map.put("user",user_list.size());

        return map;
    }
}
