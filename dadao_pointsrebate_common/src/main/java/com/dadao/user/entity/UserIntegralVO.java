package com.dadao.user.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户积分表
 *
 * @auther NFY niufuyang
 * @create 2017-08-02
 */
public class UserIntegralVO {
    private Long id;//bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '无意义代理主键',
    private Long userId;//bigint(20) NOT NULL DEFAULT '-1' COMMENT '用户ID',
    private BigDecimal integral;//decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '用户积分',
    private Long marketId;//bigint(20) NOT NULL DEFAULT '-1' COMMENT '市场表ID',
    private Date currentDate;//当天日期

    public Long getId() {
        return id==null?0:id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId==null?0:userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getIntegral() {
        return integral==null?new BigDecimal("0"):integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }

    public Long getMarketId() {
        return marketId==null?0:marketId;
    }

    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    @Override
    public String toString() {
        return "UserIntegralVO{" +
                "id=" + id +
                ", userId=" + userId +
                ", integral=" + integral +
                ", marketId=" + marketId +
                ", currentDate=" + currentDate +
                '}';
    }
}
