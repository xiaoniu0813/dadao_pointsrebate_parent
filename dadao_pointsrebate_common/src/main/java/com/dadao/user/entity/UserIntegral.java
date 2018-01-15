package com.dadao.user.entity;

import java.math.BigDecimal;

/**
 * 用户积分实体
 *
 * @auther NFY niufuyang
 * @create 2017-10-12
 */
public class UserIntegral {
    private Long id;
    private BigDecimal integral;//用户积分
    private String nickname;//用户昵称
    private Long marketId;//市场ID
    private Long userId;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
                ", integral=" + integral +
                ", nickname='" + nickname + '\'' +
                ", marketId=" + marketId +
                ", userId=" + userId +
                '}';
    }
}
