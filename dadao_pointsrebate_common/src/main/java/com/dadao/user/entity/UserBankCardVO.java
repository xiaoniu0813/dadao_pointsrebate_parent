package com.dadao.user.entity;

import com.dadao.utils.Page;

/**
 * 用户绑定银行卡表VO
 *
 * @auther NFY niufuyang
 * @create 2017-08-07
 */
public class UserBankCardVO extends Page{
    private Long cardId;//bigint(20) NOT NULL COMMENT '无意义代理主键',
            private Long userId;//bigint(20) NOT NULL DEFAULT '-1' COMMENT '用户ID',
            private String  expiryDate;//varchar(20) NOT NULL DEFAULT '' COMMENT '到期 月/年',
            private String  idCard;//varchar(20) NOT NULL DEFAULT '' COMMENT '身份证号',
            private String  bankName;//varchar(20) NOT NULL DEFAULT '' COMMENT '银行名称',
            private String  bankCardNumber;//varchar(20) NOT NULL DEFAULT '' COMMENT '银行卡号',
            private Integer bankCardType;//int(11) NOT NULL DEFAULT '0' COMMENT '银行卡类型0储蓄卡，1信用卡',
            private String  bankCardLogo;//varchar(120) NOT NULL DEFAULT '' COMMENT '银行卡logo',
            private Integer status;//int(11) NOT NULL DEFAULT '1' COMMENT '状态0无效，1有效',
            private String  name;//varchar(20) NOT NULL DEFAULT '' COMMENT '持卡人姓名',
            private String  securityCode;//varchar(20) NOT NULL DEFAULT '' COMMENT '信用卡安全码（卡片背面末三位数字）',
            private String createTime;//datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',

    public Long getCardId() {
        return cardId==null?0:cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getUserId() {
        return userId==null?0:userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getExpiryDate() {
        return expiryDate==null?"":expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getIdCard() {
        return idCard==null?"":idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getBankName() {
        return bankName==null?"":bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCardNumber() {
        return bankCardNumber==null?"":bankCardNumber;
    }

    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }

    public Integer getBankCardType() {
        return bankCardType==null?0:bankCardType;
    }

    public void setBankCardType(Integer bankCardType) {
        this.bankCardType = bankCardType;
    }

    public String getBankCardLogo() {
        return bankCardLogo==null?"":bankCardLogo;
    }

    public void setBankCardLogo(String bankCardLogo) {
        this.bankCardLogo = bankCardLogo;
    }

    public Integer getStatus() {
        return status==null?0:status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name==null?"":name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecurityCode() {
        return securityCode==null?"":securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getCreateTime() {
        return createTime==null?"":createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserBankCardVO{" +
                "cardId=" + cardId +
                ", userId=" + userId +
                ", expiryDate='" + expiryDate + '\'' +
                ", idCard='" + idCard + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankCardNumber='" + bankCardNumber + '\'' +
                ", bankCardType=" + bankCardType +
                ", bankCardLogo='" + bankCardLogo + '\'' +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", securityCode='" + securityCode + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
