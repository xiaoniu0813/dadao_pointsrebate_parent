package com.dadao.business.entity;

import com.dadao.utils.BasePage;

/**
 * 已创建账号 但没有入驻的商户
 * created by GUOYU 2017/01/19
 */
public class Cmerchant extends BasePage{

    private Long userId;//用户id
    private String nickname;//用户昵称
    private String phone;//电话
    private String picture;//照片
    private String createTime;//创建时间
    private Integer status;//状态0无效1有效
    private Integer merchant;//是否是商户（0否，1是，2后台管理员）
    private String token;//用户token

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMerchant() {
        return merchant;
    }

    public void setMerchant(Integer merchant) {
        this.merchant = merchant;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Cmerchant{" +
                "userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", picture='" + picture + '\'' +
                ", createTime='" + createTime + '\'' +
                ", status=" + status +
                ", merchant=" + merchant +
                ", token='" + token + '\'' +
                '}';
    }
}
