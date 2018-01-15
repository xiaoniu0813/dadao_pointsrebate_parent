package com.dadao.cashback.entity;

import com.dadao.utils.Page;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 返现明细
 */
public class CashbackDetailsVO extends Page{

    private Long detailsId;        //`detailsId` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    private Long recordId;         //`recordId` bigint(20) NOT NULL DEFAULT '0' COMMENT '返现记录表ID',
    private Long userId;           //`userId` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
    private String cashbackSpecificDate;   //`cashbackSpecificDate` datetime NOT NULL COMMENT '具体返现日期',
    private BigDecimal cashbackMoney;        //`cashbackMoney` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '本期返现金额',
    private BigDecimal taxPayment;           //`taxPayment` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '代缴税',
    private BigDecimal really;               //`really` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '实到金额',
    private Integer status;              //`status` int(2) NOT NULL DEFAULT '0' COMMENT '  返现状态（0未返利、1已返利）',
    private Date createTime;             //`createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',


    public Long getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(Long detailsId) {
        this.detailsId = detailsId;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCashbackSpecificDate()
    {
        return cashbackSpecificDate;
    }

    public void setCashbackSpecificDate(String cashbackSpecificDate) {
        this.cashbackSpecificDate = cashbackSpecificDate;
    }

    public BigDecimal getCashbackMoney() {
        return cashbackMoney;
    }

    public void setCashbackMoney(BigDecimal cashbackMoney) {
        this.cashbackMoney = cashbackMoney;
    }

    public BigDecimal getTaxPayment() {
        return taxPayment;
    }

    public void setTaxPayment(BigDecimal taxPayment) {
        this.taxPayment = taxPayment;
    }

    public BigDecimal getReally() {
        return really;
    }

    public void setReally(BigDecimal really) {
        this.really = really;
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
}
