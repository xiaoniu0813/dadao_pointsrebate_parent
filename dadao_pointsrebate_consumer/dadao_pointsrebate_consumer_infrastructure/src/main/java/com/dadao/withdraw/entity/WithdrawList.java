package com.dadao.withdraw.entity;

import com.dadao.utils.Page;

import java.math.BigDecimal;

/**
 * 提现列表
 *
 * @auther NFY niufuyang
 * @create 2017-08-08
 */
public class WithdrawList extends Page {
    private Long withdrawId;
    private String createTime;
    private Integer status;//状态（0平台处理中、1审核通过（提现成功）、2审核未通过（提现失败））
    private String banknameAndcardnumber;//银行名称和卡号后四位
    private BigDecimal applicationMoney;//提现申请金额
    private BigDecimal reallyMoney;//提现实到金额
    private Long userId;
    private String bankCardLogo;

    public String getBankCardLogo() {
        return bankCardLogo;
    }

    public void setBankCardLogo(String bankCardLogo) {
        this.bankCardLogo = bankCardLogo;
    }

    public Long getWithdrawId() {
        return withdrawId;
    }

    public void setWithdrawId(Long withdrawId) {
        this.withdrawId = withdrawId;
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

    public String getBanknameAndcardnumber() {
        return banknameAndcardnumber;
    }

    public void setBanknameAndcardnumber(String banknameAndcardnumber) {
        this.banknameAndcardnumber = banknameAndcardnumber;
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

    public Long getUserId() {
        return userId==null?0:userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "WithdrawList{" +
                "withdrawId=" + withdrawId +
                ", createTime='" + createTime + '\'' +
                ", status=" + status +
                ", banknameAndcardnumber='" + banknameAndcardnumber + '\'' +
                ", applicationMoney=" + applicationMoney +
                ", reallyMoney=" + reallyMoney +
                ", userId=" + userId +
                ", bankCardLogo='" + bankCardLogo + '\'' +
                '}';
    }
}
