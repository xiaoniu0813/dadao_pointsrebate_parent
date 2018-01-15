package com.dadao.capitalpool.entity;

import java.math.BigDecimal;

/**
 * 资金池
 *
 * @auther NFY niufuyang
 * @create 2017-8-24
 */
public class CapitalpoolVO {
    private Long id;//bigint(20) NOT NULL AUTO_INCREMENT COMMENT '无意义代理主键',
    private BigDecimal money;//decimal(7,2) NOT NULL DEFAULT '0.00' COMMENT '资金池金额',
    private Long marketId;//bigint(20) NOT NULL DEFAULT '-1' COMMENT '市场级别',
    private BigDecimal spareMoney;//备用资金
    private  BigDecimal toBeBackMoney;//待返金额

    public BigDecimal getToBeBackMoney() {
        return toBeBackMoney;
    }

    public void setToBeBackMoney(BigDecimal toBeBackMoney) {
        this.toBeBackMoney = toBeBackMoney;
    }

    public BigDecimal getSpareMoney() {
        return spareMoney==null?new BigDecimal("0"):spareMoney;
    }

    public void setSpareMoney(BigDecimal spareMoney) {
        this.spareMoney = spareMoney;
    }

    public Long getId() {
        return id==null?0:id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMoney() {
        return money==null?new BigDecimal("0"):money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Long getMarketId() {
        return marketId==null?0:marketId;
    }

    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }

    @Override
    public String toString() {
        return "CapitalpoolVO{" +
                "id=" + id +
                ", money=" + money +
                ", marketId=" + marketId +
                ", spareMoney=" + spareMoney +
                ", toBeBackMoney=" + toBeBackMoney +
                '}';
    }
}
