package com.dadao.bankshorthand.entity;

/**
 * @auther NFY niufuyang
 * @create 2017-10-25
 */
public class BankShorthandVO {
    private Long id;//` bigint(20) NOT NULL COMMENT '无意义代理主键',
    private String bankName;//` varchar(255) NOT NULL COMMENT '银行名称',
    private String shorthand;//` varchar(60) NOT NULL COMMENT '英文简写',
    private String cardType;//卡类型

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getShorthand() {
        return shorthand;
    }

    public void setShorthand(String shorthand) {
        this.shorthand = shorthand;
    }

    public String getCardType() {
        return cardType.equals("DC")?"借记卡":"信用卡";
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return "BankShorthand{" +
                "id=" + id +
                ", bankName='" + bankName + '\'' +
                ", shorthand='" + shorthand + '\'' +
                ", cardType='" + cardType + '\'' +
                '}';
    }
}
