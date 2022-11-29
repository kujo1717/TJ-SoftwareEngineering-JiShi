package com.example.backend.Tools;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @Author: 杨严
 * @Date: 2022/11/29/7:10 PM
 * @Description: DateTime相关的工具方法
 */
public class DateTimeUtil {

    public static Long convertTimeToLong(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(time, formatter);
        return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
