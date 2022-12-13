package com.example.backend.service;

import com.example.backend.entity.Report;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: 杨严
 * @Date: 2022/12/13/9:02 AM
 * @Description:
 */
@Service
public interface ReportService {

    /**依据条件筛选，获取举报单列表 */
    Map<String,Object> getReportList(String state, String target_type);

    /** 新增一条举报单*/
    Map<String,Object> addReport(Report report);

    /** 获取一个举报单详情*/
    Map<String,Object> getReport(Long report_id);


    /** 修改举报单状态 */
    Map<String,Object> changeReportState(String state,Long report_id);



}
