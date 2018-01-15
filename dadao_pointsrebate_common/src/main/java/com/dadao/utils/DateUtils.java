package com.dadao.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yangrui on 2017/7/14.
 */
public class DateUtils {

    static final String format = "yyyy-MM-dd HH:mm:ss";
    static final String formatToDate = "yyyy-MM-dd";
    static final String formatDate = "yyyyMMdd";
    static final String formtTime = "yyyyMMddHHmmssSSS";

    /**
     * @Author: yangrui
     * @Description: 获取系统当前时间str
     * @Date: 上午10:16 2017/7/14
     */
    public static String getCurrentTime() {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }

    /**
     * 获取当前日期   ---niufy
     *
     * @return
     */
    public static String getCurrentDate() {
        SimpleDateFormat df = new SimpleDateFormat(formatToDate);
        return df.format(new Date());
    }

    /**
     * 获取当前日期   ---niufy
     *
     * @return
     */
    public static String getDate() {
        SimpleDateFormat df = new SimpleDateFormat(formatDate);
        return df.format(new Date());
    }

    /**
     * 获取当前时间   ---niufy
     *
     * @return
     */
    public static String getTime() {
        SimpleDateFormat df = new SimpleDateFormat(formtTime);
        return df.format(new Date());
    }

    /**
     * @Author: yangrui
     * @Description: 日期字符串转时间戳
     * @Date: 下午3:13 2017/7/14
     */
    public static String date2TimeStamp(String date_str) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date date = sdf.parse(date_str);
            return String.valueOf(date.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param timestamp
     * @return
     * @Author: zyq
     * @Description 将时间戳转为"yyyy-MM-dd HH:mm:ss"
     */
    public static String timeStamp2LocalDate(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(timestamp));
    }

    /**
     * 用户端三个月内的日期
     *
     * @param a
     * @return
     */
    public static String date(int a) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(calendar.MONTH, a);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;

        String months = month + "";
        if (month < 10) {
            months = "0" + month;
        }
        String date = year + "-" + months + "-01 00:00:00";
        return date;
    }

    /**
     * @Author: niufy
     * @Description: 日期字符串转时间戳
     * @Date: 下午6:32 2017/11/15
     */
    public static String getCurrentTimeStamp() {
        String timestamp = String.valueOf(new Date().getTime());
        int length = timestamp.length();
        return timestamp.substring(0, length - 3);
    }
}
