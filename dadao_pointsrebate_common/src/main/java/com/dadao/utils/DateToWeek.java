package com.dadao.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期处理帮助类
 *
 * @auther NFY niufuyang
 * @create 2017-08-03
 */
public class DateToWeek {

    /**
     * 对一周内时间进行处理
     *
     * @param datetime
     * @return
     */
    public static String dateToWeek(String datetime) {
        //获取消费时间（小时和分钟）
        String HHss = datetime.substring(11, 16);
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //判断时间范围是否在7天内
            if (isLatestWeek(f.parse(datetime))) {
                if (timeToMove(f.parse(datetime), 0)) {
                    return "今天 " + HHss;
                } else if (timeToMove(f.parse(datetime), -1)) {
                    return "昨天 " + HHss;
                } else {
                    String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
                    Calendar cal = Calendar.getInstance(); // 获得一个日历
                    Date datet = null;
                    try {
                        datet = f.parse(datetime);
                        cal.setTime(datet);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
                    if (w < 0)
                        w = 0;
                    return weekDays[w]+" "+HHss;
                }
            } else {
                return datetime.substring(0,16);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return datetime;
    }

    public static void main(String[] args) {
        System.out.println(dateToWeek("2017-08-01 16:45:19"));
    }

    /**
     * 判断日期是否在一周内
     *
     * @param date
     * @return
     */
    public static boolean isLatestWeek(Date date) {
        Calendar calendar = Calendar.getInstance();  //得到日历
        calendar.setTime(new Date());//把当前时间赋给日历
        calendar.add(Calendar.DATE, -7);  //设置为7天前
        Date before7days = calendar.getTime();   //得到7天前的时间
        if (before7days.getTime() < date.getTime()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据参数推移时间判断dateTime是否与推移moveNum后的日期相等
     *
     * @param dateTime
     * @param moveNum
     * @return
     */
    public static boolean timeToMove(Date dateTime, int moveNum) {
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, moveNum);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        String datetime = formatter.format(dateTime);
        if (dateString.equals(datetime)) {
            return true;
        } else {
            return false;
        }
    }
}
