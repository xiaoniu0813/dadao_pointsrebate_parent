package com.dadao.user.entity;

import com.dadao.utils.BasePage;
import com.dadao.utils.QueryResult;

import java.util.Date;

/**
 * 用户基础信息表
 *
 * @auther NFY niufuyang
 * @create 2017-07-24
 */
public class UserInfo extends BasePage {
    private Long userId;// bigint(20) NOT NULL COMMENT '用户id',
    private String nickname;// varchar(64) NOT NULL DEFAULT '' COMMENT '用户昵称',
    private String phone;// varchar(20) NOT NULL DEFAULT '' COMMENT '手机号',
    private String picture;// varchar(128) NOT NULL DEFAULT '' COMMENT '用户头像',
    private Date createTime;// datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    private Long bankCardCount;//用户id对应的银行卡总数即绑定数


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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getBankCardCount() {
        return bankCardCount;
    }

    public void setBankCardCount(Long bankCardCount) {
        this.bankCardCount = bankCardCount;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", picture='" + picture + '\'' +
                ", createTime=" + createTime +
                ", bankCardCount=" + bankCardCount +
                '}';
    }
}
