package com.dadao.user.mapper.entity;

import java.math.BigDecimal;

/**
 * 用户积分
 *
 * @auther NFY niufuyang
 * @create 2017-10-10
 */
public class UserIntegral {
    private Long id;//bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '无意义代理主键',
    private Long userId;//bigint(20) NOT NULL DEFAULT '-1' COMMENT '用户ID',
    private BigDecimal integral;//decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '用户积分',
    private Long marketId;//bigint(20) NOT NULL DEFAULT '-1' COMMENT '市场表ID',

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "UserIntegral{" +
                "id=" + id +
                ", userId=" + userId +
                ", integral=" + integral +
                ", marketId=" + marketId +
                '}';
    }
}
