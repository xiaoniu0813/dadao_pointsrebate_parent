package com.dadao.cashback.entity;

public class CashbackList {

    private Long userId;// bigint(20) NOT NULL COMMENT '用户id',
    private String nickname;// varchar(64) NOT NULL DEFAULT '' COMMENT '用户昵称',
    private String phone;// varchar(20) NOT NULL DEFAULT '' COMMENT '手机号',
    private Long cashbackCount;//返利份数
    private Double cashbackMoney;//返利金额
    private Long recordId;//返现详情表中的id

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getCashbackCount() {
        return cashbackCount;
    }

    public void setCashbackCount(Long cashbackCount) {
        this.cashbackCount = cashbackCount;
    }

    public Double getCashbackMoney() {
        return cashbackMoney;
    }

    public void setCashbackMoney(Double cashbackMoney) {
        this.cashbackMoney = cashbackMoney;
    }

    @Override
    public String toString() {
        return "CashbackList{" +
                "userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", cashbackCount=" + cashbackCount +
                ", cashbackMoney=" + cashbackMoney +
                ", recordId=" + recordId +
                '}';
    }
}
