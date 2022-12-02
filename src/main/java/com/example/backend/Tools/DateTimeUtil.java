package com.example.backend.Tools;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

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

    /**
     * @description: 获取某一个月的天数
     * @author: hym
     * @date: 2022/11/25 10:35
     * @param:
     * @param: year
     * @param: month
     * @return:
     * @return: int
     **/
    public static int getDayNumOfMonth(int year, int month) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(Integer.toString(year) + "-" + Integer.toString(month) + "-01");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static Timestamp getCurrentTimestamp(){
        return new Timestamp(new Date().getTime());
    }
}
