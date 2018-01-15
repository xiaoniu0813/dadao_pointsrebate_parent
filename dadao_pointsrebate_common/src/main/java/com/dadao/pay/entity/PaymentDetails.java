package com.dadao.pay.entity;

import java.math.BigDecimal;

/**
 * 支付明细
 *
 * @auther NFY niufuyang
 * @create 2017-10-10
 */
public class PaymentDetails {
    private Long shopId;//商铺ID
    private Long userId;//用户ID
    private BigDecimal transactionAmount;//交易金额
    private boolean PayStatus;//支付状态
    private Integer PayManner;//支付方式 0钱包，1微信，2支付宝，3银联，4余额
    private String channelSequence;//通道返回订单号

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public boolean getPayStatus() {
        return PayStatus;
    }

    public void setPayStatus(boolean payStatus) {
        PayStatus = payStatus;
    }

    public Integer getPayManner() {
        return PayManner;
    }

    public void setPayManner(Integer payManner) {
        PayManner = payManner;
    }

    public String getChannelSequence() {
        return channelSequence;
    }

    public void setChannelSequence(String channelSequence) {
        this.channelSequence = channelSequence;
    }

    @Override
    public String toString() {
        return "PaymentDetails{" +
                "shopId=" + shopId +
                ", userId=" + userId +
                ", transactionAmount=" + transactionAmount +
                ", PayStatus=" + PayStatus +
                ", PayManner=" + PayManner +
                ", channelSequence='" + channelSequence + '\'' +
                '}';
    }
}
