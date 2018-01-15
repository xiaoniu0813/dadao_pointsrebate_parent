package com.dadao.transaction.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: yangrui
 * @Description: 交易记录
 * @Date: 下午5:24 2017/7/30
 */
public class TransactionRecord {
    private Long transactionId; //bigint(20) NOT NULL AUTO_INCREMENT,
    private String otherAccount; //varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '-1' COMMENT '对方账户',
    private Integer transactionType; //int(11) NOT NULL DEFAULT '-1' COMMENT '交易类型，0购物，1理财，2转账，3还款，4缴费，5红包，6提现，7充值，8奖励',
    private BigDecimal transactionAmount; //decimal(7,2) NOT NULL DEFAULT '0.00' COMMENT '交易金额',
    private String transactionDetails; //varchar(200) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '交易详情',
    private Date createtime; //datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    private Integer expenditureIncome; //int(255) NOT NULL DEFAULT '-1' COMMENT '支出收入类型。0支出，1收入',
    private Long userId; //bigint(20) NOT NULL DEFAULT '-1' COMMENT '交易用户id',
    private String payMethod; //varchar(50) NOT NULL DEFAULT '' COMMENT '支付方式',
    private BigDecimal payCanalFee; //decimal(7,2) NOT NULL DEFAULT '0.00' COMMENT '支付通道费用',
    private String serialNumber; //varchar(128) NOT NULL DEFAULT '' COMMENT '第三方流水号，退款时使用',

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getOtherAccount() {
        return otherAccount;
    }

    public void setOtherAccount(String otherAccount) {
        this.otherAccount = otherAccount;
    }

    public Integer getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(String transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getExpenditureIncome() {
        return expenditureIncome;
    }

    public void setExpenditureIncome(Integer expenditureIncome) {
        this.expenditureIncome = expenditureIncome;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public BigDecimal getPayCanalFee() {
        return payCanalFee;
    }

    public void setPayCanalFee(BigDecimal payCanalFee) {
        this.payCanalFee = payCanalFee;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return "TransactionRecord{" +
                "transactionId=" + transactionId +
                ", otherAccount='" + otherAccount + '\'' +
                ", transactionType=" + transactionType +
                ", transactionAmount=" + transactionAmount +
                ", transactionDetails='" + transactionDetails + '\'' +
                ", createtime=" + createtime +
                ", expenditureIncome=" + expenditureIncome +
                ", userId=" + userId +
                ", payMethod='" + payMethod + '\'' +
                ", payCanalFee=" + payCanalFee +
                ", serialNumber='" + serialNumber + '\'' +
                '}';
    }
}
