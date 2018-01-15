package com.dadao.cashplan.entity;

import java.math.BigDecimal;

/**
 * Created by YunQiang on 2017/9/20
 */
public class TCapitalpoolAvailable {

    private long id;

    private long marketId;

    private BigDecimal money; //当前资金池金额

    private BigDecimal availableMoney; //资金池可用金额

    private BigDecimal spareMoney;  //备用资金池金额

    private BigDecimal toBeBackMoney;   //待返金额


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMarketId() {
        return marketId;
    }

    public void setMarketId(long marketId) {
        this.marketId = marketId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getAvailableMoney() {
        return availableMoney;
    }

    public void setAvailableMoney(BigDecimal availableMoney) {
        this.availableMoney = availableMoney;
    }

    public BigDecimal getSpareMoney() {
        return spareMoney;
    }

    public void setSpareMoney(BigDecimal spareMoney) {
        this.spareMoney = spareMoney;
    }

    public BigDecimal getToBeBackMoney() {
        return toBeBackMoney;
    }

    public void setToBeBackMoney(BigDecimal toBeBackMoney) {
        this.toBeBackMoney = toBeBackMoney;
    }

    @Override
    public String toString() {
        return "TCapitalpoolAvailable{" +
                "id=" + id +
                ", marketId=" + marketId +
                ", money=" + money +
                ", availableMoney=" + availableMoney +
                ", spareMoney=" + spareMoney +
                ", toBeBackMoney=" + toBeBackMoney +
                '}';
    }
}
