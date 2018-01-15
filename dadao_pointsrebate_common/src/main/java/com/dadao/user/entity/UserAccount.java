package com.dadao.user.entity;

/**
 * Created by NFY on 2017-07-16.
 */
public class UserAccount {
    private Long userId;           //bigint(20) NOT NULL DEFAULT '-1' COMMENT '用户id',
    private String phone;             //varchar(11) NOT NULL DEFAULT '0' COMMENT '手机号',
    private String password;          //varchar(64) NOT NULL DEFAULT '0' COMMENT '密码',
    private String payPassword;       //varchar(64) NOT NULL DEFAULT '0' COMMENT '支付密码',
    private String gesturePassword;  //varchar(64) NOT NULL DEFAULT '0' COMMENT '手势密码',
    private String token;             //varchar(64) NOT NULL COMMENT '用户token',
    private Integer status;           //int(11) NOT NULL DEFAULT '1' COMMENT '状态：0无效，1有效',
    private Integer merchant;         //int(10) NOT NULL DEFAULT '0' COMMENT '是否是商户（0否，1是）',

    public UserAccount() {
    }

    public UserAccount(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId==null?0:userId;
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
}
