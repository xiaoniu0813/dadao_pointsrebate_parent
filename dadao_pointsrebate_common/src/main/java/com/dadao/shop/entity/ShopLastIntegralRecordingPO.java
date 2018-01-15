package com.dadao.shop.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商户最后一笔消费
 * Created by guoyu 2017/10/26
 */
public class ShopLastIntegralRecordingPO {
    private Long id;//商户表id
    private Long shopId;//商户id
    private Long userId;//用户id
    private String orderId;//订单编号
    private BigDecimal integral;//获得积分
    private Long marketId;//市场等级
    private Date createTime;//创建时间
    private Integer status;//给商户计算积分的状态

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public BigDecimal getIntegral() {
        return integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }

    public Long getMarketId() {
        return marketId;
    }

    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ShopLastIntegralRecordingPO{" +
                "id=" + id +
                ", shopId=" + shopId +
                ", userId=" + userId +
                ", orderId='" + orderId + '\'' +
                ", integral=" + integral +
                ", marketId=" + marketId +
                ", createTime=" + createTime +
                ", status=" + status +
                '}';
    }
}
