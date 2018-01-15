package com.dadao.cashback.entity;

import java.math.BigDecimal;

/**
 * 余额页面
 *
 * @auther NFY niufuyang
 * @create 2017-08-08
 */
public class BalanceAndFreeze {
    private BigDecimal balance;//余额
    private BigDecimal freezeMoney;//冻结金额

    public BigDecimal getBalance() {
        return balance==null?new BigDecimal("0"):balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getFreezeMoney() {
        return freezeMoney==null?new BigDecimal("0"):freezeMoney;
    }

    public void setFreezeMoney(BigDecimal freezeMoney) {
        this.freezeMoney = freezeMoney;
    }

    @Override
    public String toString() {
        return "BalanceAndFreeze{" +
                "balance=" + balance +
                ", freezeMoney=" + freezeMoney +
                '}';
    }
}
