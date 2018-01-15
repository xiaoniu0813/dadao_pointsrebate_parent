package com.dadao.user.mapper.entity;

import com.dadao.utils.Page;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by YunQiang on 2017/8/10
 */
public class UserIntegrals extends Page{

    private String name;   //店铺名/返现编码
    private BigDecimal money;  //金额
    private Integer direction; //0积分减少、1增加
    private BigDecimal integral; //产生积分
    private String integralStyle; //市场等级图标
    private Date createTime; //时间

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public BigDecimal getIntegral() {
        return integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }

    public String getIntegralStyle() {
        return integralStyle;
    }

    public void setIntegralStyle(String integralStyle) {
        this.integralStyle = integralStyle;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
