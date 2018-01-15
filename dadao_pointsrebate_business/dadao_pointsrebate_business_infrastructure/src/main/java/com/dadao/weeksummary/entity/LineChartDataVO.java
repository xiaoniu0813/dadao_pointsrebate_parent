package com.dadao.weeksummary.entity;

import java.math.BigDecimal;

/**
 * 折线图数据
 *
 * @auther NFY niufuyang
 * @create 2017-08-17
 */
public class LineChartDataVO {
    private String time;//日期
    private BigDecimal dayData;//日数据

    public String getTime() {
        return time==null?"":time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public BigDecimal getDayData() {
        return dayData==null?new BigDecimal("0"):dayData;
    }

    public void setDayData(BigDecimal dayData) {
        this.dayData = dayData;
    }

    @Override
    public String toString() {
        return "LineChartDataVO{" +
                "time='" + time + '\'' +
                ", dayData=" + dayData +
                '}';
    }
}
