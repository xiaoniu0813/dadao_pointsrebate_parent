package com.dadao.market.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MarketGradePO {

    private Long marketId;//int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    private Integer grade;//int(11) NOT NULL  COMMENT '市场等级',
    private String integralStyle;//varchar(120) NOT NULL  COMMENT '积分样式（图标）',
    private BigDecimal consumeLower;//double(10) NOT NULL  COMMENT '消费下限',
    private BigDecimal consumeUpper;//double(10) NOT NULL  COMMENT '消费上限',
    private Integer cashbackPeriod;//int(11) NOT NULL  COMMENT '返现周期',
    private Integer periodUnit;//int(10) NOT NULL  COMMENT '返现周期单位(0月、1周)',
    private Integer intervals;//int(11) NOT NULL  COMMENT '分期间隔',
    private Integer intervalsUnit;//int(10) NOT NULL  COMMENT '分期间隔单位(0月、1周)',
    private Integer integralUpper;//int(10) NOT NULL  COMMENT '积分上限',
    private Integer integralLower;//int(10) NOT NULL  COMMENT '积分下限',

    public Long getMarketId() {
        return marketId;
    }

    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getIntegralStyle() {
        return integralStyle;
    }

    public void setIntegralStyle(String integralStyle) {
        this.integralStyle = integralStyle;
    }

    public BigDecimal getConsumeLower() {
        return consumeLower;
    }

    public void setConsumeLower(BigDecimal consumeLower) {
        this.consumeLower = consumeLower;
    }

    public BigDecimal getConsumeUpper() {
        return consumeUpper;
    }

    public void setConsumeUpper(BigDecimal consumeUpper) {
        this.consumeUpper = consumeUpper;
    }

    public Integer getCashbackPeriod() {
        return cashbackPeriod;
    }

    public void setCashbackPeriod(Integer cashbackPeriod) {
        this.cashbackPeriod = cashbackPeriod;
    }

    public Integer getPeriodUnit() {
        return periodUnit;
    }

    public void setPeriodUnit(Integer periodUnit) {
        this.periodUnit = periodUnit;
    }

    public Integer getIntervals() {
        return intervals;
    }

    public void setIntervals(Integer intervals) {
        this.intervals = intervals;
    }

    public Integer getIntervalsUnit() {
        return intervalsUnit;
    }

    public void setIntervalsUnit(Integer intervalsUnit) {
        this.intervalsUnit = intervalsUnit;
    }

    public Integer getIntegralUpper() {
        return integralUpper;
    }

    public void setIntegralUpper(Integer integralUpper) {
        this.integralUpper = integralUpper;
    }

    public Integer getIntegralLower() {
        return integralLower;
    }

    public void setIntegralLower(Integer integralLower) {
        this.integralLower = integralLower;
    }

    @Override
    public String toString() {
        return "MarketGradePO{" +
                "marketId=" + marketId +
                ", grade=" + grade +
                ", integralStyle='" + integralStyle + '\'' +
                ", consumeLower=" + consumeLower +
                ", consumeUpper=" + consumeUpper +
                ", cashbackPeriod=" + cashbackPeriod +
                ", periodUnit=" + periodUnit +
                ", intervals=" + intervals +
                ", intervalsUnit=" + intervalsUnit +
                ", integralUpper=" + integralUpper +
                ", integralLower=" + integralLower +
                '}';
    }
}
