package com.dadao.refunds.mapper.entity;

import com.dadao.refunds.entity.RefundsApplicationPO;

/**
 * 退款列表展示类
 * @author YunQiang
 */
public class RefundsMessage extends RefundsApplicationPO{

    /**
     * 申请退款的用户手机号
     */
    private String consumerPhone;

    /**
     * 申请退款的商户手机号
     */
    private String businessPhone;

    /**
     * 退款金额
     */
    private String amount;

    public String getConsumerPhone() {
        return consumerPhone;
    }

    public void setConsumerPhone(String consumerPhone) {
        this.consumerPhone = consumerPhone;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
