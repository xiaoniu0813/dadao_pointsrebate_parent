package com.dadao.order.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: yangrui
 * @Description: 订单表
 * @Date: 下午4:36 2017/7/30
 */
public class Order {
    private Integer id; //int(11) NOT NULL COMMENT '无意义代理主键',
    private Long shopId;
    private String orderId; //varchar(32) NOT NULL COMMENT '订单编号',
    private Long userId; //bigint(20) NOT NULL DEFAULT '-1' COMMENT '用户id',
    private BigDecimal amount; //decimal(7,2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    private Integer marketId; //int(11) NOT NULL DEFAULT '0' COMMENT '市场id',
    private Integer payMethod; //int(11) NOT NULL DEFAULT '0' COMMENT '支付方式0钱包，1微信，2支付宝，3银联',
    private Integer orderStatus; //tinyint(4) NOT NULL DEFAULT '0' COMMENT '订单状态-1已删除订单，0待支付，1取消订单，2已支付，3,待发货，4,已发货，5交易完成，6退款申请，7退款中，8退款失败，9退款成功',
    private String description; //varchar(256) NOT NULL DEFAULT '' COMMENT '订单描述',
    private String updateTime; //datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    private String payTime; //datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '付款时间',
    private Date createTime; //datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
    private String channelSequence; //varchar(64) NOT NULL DEFAULT '' COMMENT '通道返回订单号',
    private String channelRetCode; //varchar(64) NOT NULL DEFAULT '' COMMENT '通道返回码',
    private String channelResponse; //text NOT NULL COMMENT '通道返回数据',
    private String refundSequence; //varchar(64) NOT NULL DEFAULT '' COMMENT '退款单号',
    private String channelRefundSequence; //varchar(64) NOT NULL DEFAULT '' COMMENT '退款渠道方单号',

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getMarketId() {
        return marketId;
    }

    public void setMarketId(Integer marketId) {
        this.marketId = marketId;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getChannelSequence() {
        return channelSequence;
    }

    public void setChannelSequence(String channelSequence) {
        this.channelSequence = channelSequence;
    }

    public String getChannelRetCode() {
        return channelRetCode;
    }

    public void setChannelRetCode(String channelRetCode) {
        this.channelRetCode = channelRetCode;
    }

    public String getChannelResponse() {
        return channelResponse;
    }

    public void setChannelResponse(String channelResponse) {
        this.channelResponse = channelResponse;
    }

    public String getRefundSequence() {
        return refundSequence;
    }

    public void setRefundSequence(String refundSequence) {
        this.refundSequence = refundSequence;
    }

    public String getChannelRefundSequence() {
        return channelRefundSequence;
    }

    public void setChannelRefundSequence(String channelRefundSequence) {
        this.channelRefundSequence = channelRefundSequence;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", shopId=" + shopId +
                ", orderId='" + orderId + '\'' +
                ", userId=" + userId +
                ", amount=" + amount +
                ", marketId=" + marketId +
                ", payMethod=" + payMethod +
                ", orderStatus=" + orderStatus +
                ", description='" + description + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", payTime='" + payTime + '\'' +
                ", createTime=" + createTime +
                ", channelSequence='" + channelSequence + '\'' +
                ", channelRetCode='" + channelRetCode + '\'' +
                ", channelResponse='" + channelResponse + '\'' +
                ", refundSequence='" + refundSequence + '\'' +
                ", channelRefundSequence='" + channelRefundSequence + '\'' +
                '}';
    }
}
