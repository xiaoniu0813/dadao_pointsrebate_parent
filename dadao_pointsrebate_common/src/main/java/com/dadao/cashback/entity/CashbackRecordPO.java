package com.dadao.cashback.entity;


import com.dadao.utils.Page;
import com.dadao.utils.QueryResult;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 *返现记录表
 */
public class CashbackRecordPO extends Page {

    private Long recordId;//`recordId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    private String recordCoding;//`recordCoding` varchar(20) NOT NULL DEFAULT '0' COMMENT '返现编码（利用本次返现时间和市场级别组合生成）',
    private Long marketID;//`marketID` bigint(20) NOT NULL DEFAULT '0' COMMENT '多级市场ID',
    private Integer currentIntegralUpper;// `currentIntegralUpper` int(10) NOT NULL DEFAULT '-1' COMMENT '当前返现积分上限',
    private BigDecimal cashbackSumMoney;//`cashbackSumMoney` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '返现总金额',
    private Integer cashbackNumber;//`cashbackNumber` int(6) NOT NULL DEFAULT '0' COMMENT '返现人数',
    private Double cashbackMultiple;// `cashbackMultiple` int(4) NOT NULL DEFAULT '0' COMMENT '返现倍数',
    private Integer cashbackPeriod;//`cashbackPeriod` int(4) NOT NULL DEFAULT '0' COMMENT '本次返现周期',
    private Integer periodUnit;// `periodUnit` int(10) NOT NULL DEFAULT '0' COMMENT '返现周期单位(0月、1周)',
    private Integer intervals;//`intervals` int(11) NOT NULL DEFAULT '0' COMMENT '分期间隔',
    private Integer intervalsUnit;//`intervalsUnit` int(10) NOT NULL DEFAULT '0' COMMENT '分期间隔单位(0月、1周)',
    private String cashbackSpecificDate;//`cashbackSpecificDate` int(4) NOT NULL DEFAULT '0' COMMENT '返现具体日期',
    private Integer status;//`status` int(2) NOT NULL DEFAULT '0' COMMENT '返现状态（0未反完，1已返完）',
    private Date createTime;//`createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',

    public CashbackRecordPO() {
    }

    public CashbackRecordPO(String recordCoding, Double cashbackMultiple, Integer cashbackPeriod, Integer periodUnit, Integer intervals, Integer intervalsUnit, String cashbackSpecificDate, Integer status) {
        this.recordCoding = recordCoding;
        this.cashbackMultiple = cashbackMultiple;
        this.cashbackPeriod = cashbackPeriod;
        this.periodUnit = periodUnit;
        this.intervals = intervals;
        this.intervalsUnit = intervalsUnit;
        this.cashbackSpecificDate = cashbackSpecificDate;
        this.status = status;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getRecordCoding() {
        return recordCoding;
    }

    public void setRecordCoding(String recordCoding) {
        this.recordCoding = recordCoding;
    }

    public Long getMarketID() {
        return marketID;
    }

    public void setMarketID(Long marketID) {
        this.marketID = marketID;
    }

    public Integer getCurrentIntegralUpper() {
        return currentIntegralUpper;
    }

    public void setCurrentIntegralUpper(Integer currentIntegralUpper) {
        this.currentIntegralUpper = currentIntegralUpper;
    }

    public BigDecimal getCashbackSumMoney() {
        return cashbackSumMoney;
    }

    public void setCashbackSumMoney(BigDecimal cashbackSumMoney) {
        this.cashbackSumMoney = cashbackSumMoney;
    }

    public Integer getCashbackNumber() {
        return cashbackNumber;
    }

    public void setCashbackNumber(Integer cashbackNumber) {
        this.cashbackNumber = cashbackNumber;
    }

    public Double getCashbackMultiple() {
        return cashbackMultiple;
    }

    public void setCashbackMultiple(Double cashbackMultiple) {
        this.cashbackMultiple = cashbackMultiple;
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

    public String getCashbackSpecificDate() {
        return cashbackSpecificDate;
    }

    public void setCashbackSpecificDate(String cashbackSpecificDate) {
        this.cashbackSpecificDate = cashbackSpecificDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "CashbackRecordPO{" +
                "recordId=" + recordId +
                ", recordCoding='" + recordCoding + '\'' +
                ", marketID=" + marketID +
                ", currentIntegralUpper=" + currentIntegralUpper +
                ", cashbackSumMoney=" + cashbackSumMoney +
                ", cashbackNumber=" + cashbackNumber +
                ", cashbackMultiple=" + cashbackMultiple +
                ", cashbackPeriod=" + cashbackPeriod +
                ", periodUnit=" + periodUnit +
                ", intervals=" + intervals +
                ", intervalsUnit=" + intervalsUnit +
                ", cashbackSpecificDate=" + cashbackSpecificDate +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
