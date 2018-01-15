package com.dadao.user.mapper.entity;

import com.dadao.utils.QueryResult;

import java.math.BigDecimal;
/**
 * @auther GUOYU 2017/12/04
 */
public class UserDealRecord {

    private BigDecimal totalInteger;//用户当前总积分
    private BigDecimal currentDayInteger;//用户今日获得总积分
    private Integer currentMonthCount;//用户当月交易完成数量
    private QueryResult userDeals;//积分记录

    public QueryResult getUserDeals() {
        return userDeals;
    }

    public void setUserDeals(QueryResult userDeals) {
        this.userDeals = userDeals;
    }

    public BigDecimal getTotalInteger() {
        return totalInteger;
    }

    public void setTotalInteger(BigDecimal totalInteger) {
        this.totalInteger = totalInteger;
    }

    public BigDecimal getCurrentDayInteger() {
        return currentDayInteger;
    }

    public void setCurrentDayInteger(BigDecimal currentDayInteger) {
        this.currentDayInteger = currentDayInteger;
    }

    public Integer getCurrentMonthCount() {
        return currentMonthCount;
    }

    public void setCurrentMonthCount(Integer currentMonthCount) {
        this.currentMonthCount = currentMonthCount;
    }

    @Override
    public String toString() {
        return "UserDealRecord{" +
                "totalInteger=" + totalInteger +
                ", currentDayInteger=" + currentDayInteger +
                ", currentMonthCount=" + currentMonthCount +
                ", userDeals=" + userDeals +
                '}';
    }
}
