package com.dadao.user.entity;

import com.dadao.utils.DateToWeek;
import com.dadao.utils.Page;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户交易记录
 *
 * @auther NFY niufuyang
 * @create 2017-07-31
 */
public class UserTransactionRecodVO extends Page {
    private Long transactionId;// bigint(20) NOT NULL AUTO_INCREMENT,
    private String otherAccount;// varchar(100) CHARACTER SET utf8 NOT NULL DEFAULT '-1' COMMENT '对方账户',
    private Integer transactionType;// int(11) NOT NULL DEFAULT '-1' COMMENT '交易类型，0返利，1转入，2转出，3提现，4支付',
    private BigDecimal transactionAmount;// decimal(7,2) NOT NULL DEFAULT '0.00' COMMENT '交易金额',
    private String transactionDetails;// varchar(200) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '交易详情',
    private String createtime;// datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    private Integer expenditureIncome;// int(255) NOT NULL DEFAULT '-1' COMMENT '支出收入类型。0支出，1收入',
    private Long userId;// bigint(20) NOT NULL DEFAULT '-1' COMMENT '交易用户id',
    private Integer payMethod;// varchar(50) NOT NULL DEFAULT '' COMMENT '支付方式0钱包，1微信，2支付宝，3银联，4余额',
    private BigDecimal payCanalFee;// decimal(7,2) NOT NULL DEFAULT '0.00' COMMENT '支付通道费用',
    private String serialNumber;// varchar(128) NOT NULL DEFAULT '' COMMENT '第三方流水号，退款时使用',
    private BigDecimal commission;//平台佣金
    private BigDecimal actualIncome;//实际收入

    public BigDecimal getCommission() {
        return commission==null?new BigDecimal("0"):commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public BigDecimal getActualIncome() {
        return actualIncome==null?new BigDecimal("0"):actualIncome;
    }

    public void setActualIncome(BigDecimal actualIncome) {
        this.actualIncome = actualIncome;
    }

    public Long getTransactionId() {
        return transactionId == null ? 0 : transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getOtherAccount() {
        return otherAccount == null ? "" : otherAccount;
    }

    public void setOtherAccount(String otherAccount) {
        this.otherAccount = otherAccount;
    }

    public Integer getTransactionType() {
        return transactionType == null ? 0 : transactionType;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount == null ? new BigDecimal("0") : transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionDetails() {
        return transactionDetails == null ? "" : transactionDetails;
    }

    public void setTransactionDetails(String transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    public String getCreatetime() {
        return DateToWeek.dateToWeek(createtime);
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Integer getExpenditureIncome() {
        return expenditureIncome == null ? 0 : expenditureIncome;
    }

    public void setExpenditureIncome(Integer expenditureIncome) {
        this.expenditureIncome = expenditureIncome;
    }

    public Long getUserId() {
        return userId == null ? 0 : userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getPayMethod() {
        return payMethod == null ? 0 : payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public BigDecimal getPayCanalFee() {
        return payCanalFee == null ? new BigDecimal("0") : payCanalFee;
    }

    public void setPayCanalFee(BigDecimal payCanalFee) {
        this.payCanalFee = payCanalFee;
    }

    public String getSerialNumber() {
        return serialNumber == null ? "" : serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return "UserTransactionRecodVO{" +
                "transactionId=" + transactionId +
                ", otherAccount='" + otherAccount + '\'' +
                ", transactionType=" + transactionType +
                ", transactionAmount=" + transactionAmount +
                ", transactionDetails='" + transactionDetails + '\'' +
                ", createtime='" + createtime + '\'' +
                ", expenditureIncome=" + expenditureIncome +
                ", userId=" + userId +
                ", payMethod=" + payMethod +
                ", payCanalFee=" + payCanalFee +
                ", serialNumber='" + serialNumber + '\'' +
                ", commission=" + commission +
                ", actualIncome=" + actualIncome +
                '}';
    }
}
