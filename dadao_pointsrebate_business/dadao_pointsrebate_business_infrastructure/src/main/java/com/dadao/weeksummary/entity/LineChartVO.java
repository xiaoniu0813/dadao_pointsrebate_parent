package com.dadao.weeksummary.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * 折线图
 *
 * @auther NFY niufuyang
 * @create 2017-08-17
 */
public class LineChartVO {
    private BigDecimal totalRevenue;//总收入
    private BigDecimal dayMaxIncome;//日最高收入
    private BigDecimal averageDailyIncome;//日均收入
    private List lineChartlist;//折线图列表
    private Long userId;
    private Integer statisticsDate;//统计日期

    public BigDecimal getTotalRevenue() {
        return totalRevenue==null?new BigDecimal(0):totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public BigDecimal getDayMaxIncome() {
        return dayMaxIncome==null?new BigDecimal(0):dayMaxIncome;
    }

    public void setDayMaxIncome(BigDecimal dayMaxIncome) {
        this.dayMaxIncome = dayMaxIncome;
    }

    public BigDecimal getAverageDailyIncome() {
        return averageDailyIncome==null?new BigDecimal(0):averageDailyIncome;
    }

    public void setAverageDailyIncome(BigDecimal averageDailyIncome) {
        this.averageDailyIncome = averageDailyIncome;
    }

    public List getLineChartlist() {
        return lineChartlist;
    }

    public void setLineChartlist(List lineChartlist) {
        this.lineChartlist = lineChartlist;
    }

    public Long getUserId() {
        return userId==null?0:userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getStatisticsDate() {
        return statisticsDate==null?0:statisticsDate;
    }

    public void setStatisticsDate(Integer statisticsDate) {
        this.statisticsDate = statisticsDate;
    }

    @Override
    public String toString() {
        return "LineChartVO{" +
                "totalRevenue=" + totalRevenue +
                ", dayMaxIncome=" + dayMaxIncome +
                ", averageDailyIncome=" + averageDailyIncome +
                ", lineChartlist=" + lineChartlist +
                ", userId=" + userId +
                ", statisticsDate=" + statisticsDate +
                '}';
    }
}
