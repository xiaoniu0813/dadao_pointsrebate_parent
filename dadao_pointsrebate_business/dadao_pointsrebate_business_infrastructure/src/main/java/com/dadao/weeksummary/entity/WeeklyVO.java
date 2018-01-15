package com.dadao.weeksummary.entity;

import java.math.BigDecimal;

/**
 * 周报
 *
 * @auther NFY niufuyang
 * @create 2017-08-16
 */
public class WeeklyVO {
    private double totalTurnover;//总营业额
    private double averageAmount;//平均交易额
    private Integer transactionNum;//交易笔数
    private double totalTurnoverRing;//营业额环比
    private double averageAmountRing;//平均交易额环比
    private double transactionNumRing;//交易笔数环比

    public double getTotalTurnover() {
        return totalTurnover;
    }

    public void setTotalTurnover(double totalTurnover) {
        this.totalTurnover = totalTurnover;
    }

    public double getAverageAmount() {
        return averageAmount;
    }

    public void setAverageAmount(double averageAmount) {
        this.averageAmount = averageAmount;
    }

    public Integer getTransactionNum() {
        return transactionNum;
    }

    public void setTransactionNum(Integer transactionNum) {
        this.transactionNum = transactionNum;
    }

    public double getTotalTurnoverRing() {
        return totalTurnoverRing;
    }

    public void setTotalTurnoverRing(double totalTurnoverRing) {
        this.totalTurnoverRing = totalTurnoverRing;
    }

    public double getAverageAmountRing() {
        return averageAmountRing;
    }

    public void setAverageAmountRing(double averageAmountRing) {
        this.averageAmountRing = averageAmountRing;
    }

    public double getTransactionNumRing() {
        return transactionNumRing;
    }

    public void setTransactionNumRing(double transactionNumRing) {
        this.transactionNumRing = transactionNumRing;
    }

    @Override
    public String toString() {
        return "WeeklyVO{" +
                "totalTurnover=" + totalTurnover +
                ", averageAmount=" + averageAmount +
                ", transactionNum=" + transactionNum +
                ", totalTurnoverRing=" + totalTurnoverRing +
                ", averageAmountRing=" + averageAmountRing +
                ", transactionNumRing=" + transactionNumRing +
                '}';
    }
}
