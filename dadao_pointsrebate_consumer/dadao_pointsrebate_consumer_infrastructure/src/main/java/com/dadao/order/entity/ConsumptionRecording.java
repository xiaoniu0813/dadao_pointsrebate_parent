package com.dadao.order.entity;

import com.dadao.utils.DateToWeek;
import com.dadao.utils.Page;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 消费记录
 *
 * @auther NFY niufuyang
 * @create 2017-08-01
 */
public class ConsumptionRecording extends Page {
    private Long id; //订单表ID
    private String shopName;//商铺名称
    private Integer orderStatus;//订单状态
    private BigDecimal amount;//订单金额
    private String createTime;//交易创建时间
    private Integer payMethod;//支付方式
    private String orderId;//订单编号
    private Long userId;

    public Long getUserId() {
        return userId==null?0:userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCreateTime() {
        return DateToWeek.dateToWeek(createTime);
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "ConsumptionRecording{" +
                "id=" + id +
                ", shopName='" + shopName + '\'' +
                ", orderStatus=" + orderStatus +
                ", amount=" + amount +
                ", createTime='" + createTime + '\'' +
                ", payMethod=" + payMethod +
                ", orderId='" + orderId + '\'' +
                ", userId=" + userId +
                '}';
    }
}
