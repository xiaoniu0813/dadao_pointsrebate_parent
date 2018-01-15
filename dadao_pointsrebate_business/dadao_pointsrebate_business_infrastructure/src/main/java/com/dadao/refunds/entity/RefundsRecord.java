package com.dadao.refunds.entity;

import com.dadao.utils.Page;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by YunQiang on 2017/8/9
 */
public class RefundsRecord extends Page{

    private Long id;//
    private Long refundsId; //退款表ID
    private String shopName;    //商铺名称
    private BigDecimal amount;  //交易金额
    private Integer status; //交易状态
    private Integer payMethod;  //退款方式
    private BigDecimal deduct;  //支付通道费用
    private BigDecimal deductReally;    //实际扣款金额
    private String orderId; //原单交易编号
    private String refundSequence;  //交易编号
    private Date dealTime;  //交易时间
    private Date refundTime;    //申请退款时间
    private String userDescription; //用户原因

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRefundsId() {
        return refundsId;
    }

    public void setRefundsId(Long refundsId) {
        this.refundsId = refundsId;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getDealTime() {
        return dealTime;
    }

    public void setDealTime(Date dealTime) {
        this.dealTime = dealTime;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public BigDecimal getDeduct() {
        return deduct;
    }

    public void setDeduct(BigDecimal deduct) {
        this.deduct = deduct;
    }

    public BigDecimal getDeductReally() {
        return deductReally;
    }

    public void setDeductReally(BigDecimal deductReally) {
        this.deductReally = deductReally;
    }

    public String getRefundSequence() {
        return refundSequence;
    }

    public void setRefundSequence(String refundSequence) {
        this.refundSequence = refundSequence;
    }
}
