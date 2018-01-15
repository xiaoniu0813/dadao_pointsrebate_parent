package com.dadao.user.entity;

import java.math.BigDecimal;

/**
 * Created by YunQiang on 2017/10/26
 */
public class BusinessCashIntegral {

    private Integer id; //商户积分钱包id
    private Long marketId;  //市场id
    private Long userId;    //商户Id
    private String phone;   //商户手机号
    private BigDecimal integral;    //商户积分
    private BigDecimal businessCashRate;    //商户返现倍率
    private BigDecimal actualSalary;    //实发金额

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getMarketId() {
        return marketId;
    }

    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getIntegral() {
        return integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }

    public BigDecimal getBusinessCashRate() {
        return businessCashRate;
    }

    public void setBusinessCashRate(BigDecimal businessCashRate) {
        this.businessCashRate = businessCashRate;
    }

    public BigDecimal getActualSalary() {
        return actualSalary;
    }

    public void setActualSalary(BigDecimal actualSalary) {
        this.actualSalary = actualSalary;
    }

    @Override
    public String toString() {
        return "BusinessCashIntegral{" +
                "id=" + id +
                ", marketId=" + marketId +
                ", userId=" + userId +
                ", phone='" + phone + '\'' +
                ", integral=" + integral +
                ", businessCashRate=" + businessCashRate +
                ", actualSalary=" + actualSalary +
                '}';
    }
}
