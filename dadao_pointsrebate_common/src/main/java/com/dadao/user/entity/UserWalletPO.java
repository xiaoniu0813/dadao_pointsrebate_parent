package com.dadao.user.entity;

import java.math.BigDecimal;

/**
 * 用户余额表PO
 *
 * @auther NFY niufuyang
 * @create 2017-08-07
 */
public class UserWalletPO {
    private Long userId;// bigint(20) NOT NULL DEFAULT '-1' COMMENT '用户id',
    private BigDecimal balance;// decimal(7,2) NOT NULL DEFAULT '0.00' COMMENT '余额',
    private BigDecimal margin;//保证金
    private Integer status;// int(11) NOT NULL DEFAULT '1' COMMENT '状态：0冻结，1有效',

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getMargin() {
        return margin;
    }

    public void setMargin(BigDecimal margin) {
        this.margin = margin;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserWalletPO{" +
                "userId=" + userId +
                ", balance=" + balance +
                ", margin=" + margin +
                ", status=" + status +
                '}';
    }
}
