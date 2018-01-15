package com.dadao.user.entity;

/**
 * @Author: yangrui
 * @Description: 用户表
 * @Date: 下午12:15 2017/7/30
 */
public class UserAccount {
    private Long userId; //bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
    private String phone; //varchar(11) NOT NULL DEFAULT '0' COMMENT '手机号',
    private String password; //varchar(64) NOT NULL DEFAULT '0' COMMENT '密码',
    private String payPassword; //varchar(64) NOT NULL DEFAULT '0' COMMENT '支付密码',
    private String gesturePassword;//varchar(64) NOT NULL DEFAULT '0' COMMENT '手势密码',
    private String token; //varchar(64) NOT NULL COMMENT '用户token',
    private Integer status; //int(11) NOT NULL DEFAULT '1' COMMENT '状态：0无效，1有效',
    private Integer merchant; //int(10) NOT NULL DEFAULT '0' COMMENT '是否是商户（0否，1是）',

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getGesturePassword() {
        return gesturePassword;
    }

    public void setGesturePassword(String gesturePassword) {
        this.gesturePassword = gesturePassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    @Override
    public String toString() {
        return "UserAccount{" +
                "userId=" + userId +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", payPassword='" + payPassword + '\'' +
                ", gesturePassword='" + gesturePassword + '\'' +
                ", token='" + token + '\'' +
                ", status=" + status +
                ", merchant=" + merchant +
                '}';
    }
}
