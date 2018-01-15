package com.dadao.user.entity;

import com.dadao.utils.Page;

/**
 * 用户积分列表
 *
 * @auther NFY niufuyang
 * @create 2017-8-24
 */
public class UserIntegral extends Page {
    private Double integral;//用户积分
    private String nickname;//用户昵称
    private Long marketId;//市场ID
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getIntegral() {
        return integral;
    }

    public void setIntegral(Double integral) {
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
                "integral=" + integral +
                ", nickname='" + nickname + '\'' +
                ", marketId=" + marketId +
                ", userId=" + userId +
                '}';
    }
}
