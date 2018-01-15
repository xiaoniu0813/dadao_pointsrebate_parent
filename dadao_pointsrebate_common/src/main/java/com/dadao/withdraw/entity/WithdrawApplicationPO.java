package com.dadao.withdraw.entity;

import java.math.BigDecimal;

/**
 * 提现申请PO
 *
 * @auther NFY niufuyang
 * @create 2017-08-08
 */
public class WithdrawApplicationPO {
    private Long withdrawId;//bigint(20) NOT NULL AUTO_INCREMENT COMMENT '无意义代理主键',
    private Long userId;//bigint(20) NOT NULL DEFAULT '-1' COMMENT '用户ID·',
    private Long cardId;//bigint(20) NOT NULL DEFAULT '-1' COMMENT '用户绑定卡ID（提现到哪个卡）',
    private BigDecimal taxMoney;//decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '扣税金额',
    private BigDecimal platformFee;//decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '平台手续费',
    private BigDecimal applicationMoney;//decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '申请提现金额',
    private BigDecimal reallyMoney;//decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '实到金额',
    private Integer status;//int(11) NOT NULL DEFAULT '0' COMMENT '状态（0平台处理中、1审核通过（提现成功）、2审核未通过（提现失败））',
    private String description;//varchar(200) NOT NULL DEFAULT '' COMMENT '审核描述',
    private String createTime;//datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',

    public Long getWithdrawId() {
        return withdrawId;
    }

    public void setWithdrawId(Long withdrawId) {
        this.withdrawId = withdrawId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public BigDecimal getTaxMoney() {
        return taxMoney;
    }

    public void setTaxMoney(BigDecimal taxMoney) {
        this.taxMoney = taxMoney;
    }

    public BigDecimal getPlatformFee() {
        return platformFee;
    }

    public void setPlatformFee(BigDecimal platformFee) {
        this.platformFee = platformFee;
    }

    public BigDecimal getApplicationMoney() {
        return applicationMoney;
    }

    public void setApplicationMoney(BigDecimal applicationMoney) {
        this.applicationMoney = applicationMoney;
    }

    public BigDecimal getReallyMoney() {
        return reallyMoney;
    }

    public void setReallyMoney(BigDecimal reallyMoney) {
        this.reallyMoney = reallyMoney;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "WithdrawApplicationPO{" +
                "withdrawId=" + withdrawId +
                ", userId=" + userId +
                ", cardId=" + cardId +
                ", taxMoney=" + taxMoney +
                ", platformFee=" + platformFee +
                ", applicationMoney=" + applicationMoney +
                ", reallyMoney=" + reallyMoney +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
