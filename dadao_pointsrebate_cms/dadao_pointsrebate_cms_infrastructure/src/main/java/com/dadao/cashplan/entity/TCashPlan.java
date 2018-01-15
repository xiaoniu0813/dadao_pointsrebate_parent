package com.dadao.cashplan.entity;

import java.util.Date;

public class TCashPlan {

    private String recordCoding;

    private Long marketID;

    private Date cashbackSpecificDate;

    private Integer status;

    private Date createTime;

    public TCashPlan() {
    }

    public TCashPlan(String recordCoding, Long marketID, Date cashbackSpecificDate, Integer status, Date createTime) {
        this.recordCoding = recordCoding;
        this.marketID = marketID;
        this.cashbackSpecificDate = cashbackSpecificDate;
        this.status = status;
        this.createTime = createTime;
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

    public Date getCashbackSpecificDate() {
        return cashbackSpecificDate;
    }

    public void setCashbackSpecificDate(Date cashbackSpecificDate) {
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
        return "TCashPlan{" +
                "recordCoding='" + recordCoding + '\'' +
                ", marketID=" + marketID +
                ", cashbackSpecificDate=" + cashbackSpecificDate +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}