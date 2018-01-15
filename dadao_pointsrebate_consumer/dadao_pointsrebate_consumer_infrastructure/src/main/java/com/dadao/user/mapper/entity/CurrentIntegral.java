package com.dadao.user.mapper.entity;

import java.math.BigDecimal;

/**
 * @auther GUOYU 2017/12/04
 */
public class CurrentIntegral {

    private BigDecimal currentMonthIntegral;//用户当月获得积分
    private BigDecimal currentMonthUseIntegerl;//用户当月使用的积分

    public BigDecimal getCurrentMonthIntegral() {
        return currentMonthIntegral;
    }

    public void setCurrentMonthIntegral(BigDecimal currentMonthIntegral) {
        this.currentMonthIntegral = currentMonthIntegral;
    }

    public BigDecimal getCurrentMonthUseIntegerl() {
        return currentMonthUseIntegerl;
    }

    public void setCurrentMonthUseIntegerl(BigDecimal currentMonthUseIntegerl) {
        this.currentMonthUseIntegerl = currentMonthUseIntegerl;
    }

    @Override
    public String toString() {
        return "CurrentIntegral{" +
                "currentMonthIntegral=" + currentMonthIntegral +
                ", currentMonthUseIntegerl=" + currentMonthUseIntegerl +
                '}';
    }
}
