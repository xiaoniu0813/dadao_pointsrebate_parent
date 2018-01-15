package com.dadao.cashplan.entity;

import java.math.BigDecimal;

/**
 * Created by YunQiang on 2017/9/13
 */
public class TUserEffectiveIntegral {

    private long userId; //userId

    private long marketId;  //市场id

    private Integer grade;  //市场等级

    private BigDecimal currentIntegral;   //用户当前积分

    private BigDecimal effectiveIntegral;  //用户有效积分

    private BigDecimal currentIntegralUpper;    //当前积分上限

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getMarketId() {
        return marketId;
    }

    public void setMarketId(long marketId) {
        this.marketId = marketId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public BigDecimal getCurrentIntegral() {
        return currentIntegral;
    }

    public void setCurrentIntegral(BigDecimal currentIntegral) {
        this.currentIntegral = currentIntegral;
    }

    public BigDecimal getEffectiveIntegral() {
        return effectiveIntegral;
    }

    public void setEffectiveIntegral(BigDecimal effectiveIntegral) {
        this.effectiveIntegral = effectiveIntegral;
    }

    public BigDecimal getCurrentIntegralUpper() {
        return currentIntegralUpper;
    }

    public void setCurrentIntegralUpper(BigDecimal currentIntegralUpper) {
        this.currentIntegralUpper = currentIntegralUpper;
    }

    @Override
    public String toString() {
        return "TUserEffectiveIntegral{" +
                "userId=" + userId +
                ", marketId=" + marketId +
                ", grade=" + grade +
                ", currentIntegral=" + currentIntegral +
                ", effectiveIntegral=" + effectiveIntegral +
                ", currentIntegralUpper=" + currentIntegralUpper +
                '}';
    }
}
