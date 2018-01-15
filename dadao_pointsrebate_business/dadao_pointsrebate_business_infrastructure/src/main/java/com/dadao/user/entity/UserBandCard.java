package com.dadao.user.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author: yangrui
 * @Description: 银行卡
 * @Date: 下午1:50 2017/8/13
 */
public class UserBandCard {
    private Long cardId; //bigint(20) NOT NULL AUTO_INCREMENT COMMENT '无意义代理主键',
    private Long userId; //bigint(20) NOT NULL DEFAULT '-1' COMMENT '用户ID',
    private String expiryDate; //varchar(20) NOT NULL DEFAULT '' COMMENT '到期 月/年',
    @NotEmpty(message = "身份证号不能为空")
    private String idCard; //varchar(20) NOT NULL DEFAULT '' COMMENT '身份证号',
    private String bankName; //varchar(20) NOT NULL DEFAULT '' COMMENT '银行名称',
    @NotEmpty(message = "银行卡号不能为空")
    private String bankCardNumber; //varchar(20) NOT NULL DEFAULT '' COMMENT '银行卡号',
    private Integer bankCardType; //int(11) NOT NULL DEFAULT '0' COMMENT '银行卡类型0储蓄卡，1信用卡',
    private String bankCardLogo; //varchar(120) NOT NULL DEFAULT '' COMMENT '银行卡logo',
    @NotEmpty(message = "手机号不能为空")
    private String phone;//手机号
    private Integer status; //int(11) NOT NULL DEFAULT '1' COMMENT '状态0无效，1有效',
    @NotEmpty(message = "持卡人姓名不能为空")
    private String name; //varchar(20) NOT NULL DEFAULT '' COMMENT '持卡人姓名',
    private String securityCode; //varchar(20) NOT NULL DEFAULT '' COMMENT '信用卡安全码（卡片背面末三位数字）',
    private Date createTime; //datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCardNumber() {
        return bankCardNumber;
    }

    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }

    public Integer getBankCardType() {
        return bankCardType;
    }

    public void setBankCardType(Integer bankCardType) {
        this.bankCardType = bankCardType;
    }

    public String getBankCardLogo() {
        return bankCardLogo;
    }

    public void setBankCardLogo(String bankCardLogo) {
        this.bankCardLogo = bankCardLogo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserBandCard{" +
                "cardId=" + cardId +
                ", userId=" + userId +
                ", expiryDate='" + expiryDate + '\'' +
                ", idCard='" + idCard + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankCardNumber='" + bankCardNumber + '\'' +
                ", bankCardType=" + bankCardType +
                ", bankCardLogo='" + bankCardLogo + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", securityCode='" + securityCode + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
