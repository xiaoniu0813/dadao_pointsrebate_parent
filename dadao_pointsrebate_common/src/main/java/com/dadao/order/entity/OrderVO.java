package com.dadao.order.entity;

import com.dadao.utils.Page;

import javax.xml.soap.Text;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单表
 *
 * @auther NFY niufuyang
 * @create 2017-07-31
 */
public class OrderVO extends Page {
    private Long id;//int(11) NOT NULL AUTO_INCREMENT COMMENT '无意义代理主键',
    private Long shopId;//bigint(20) NOT NULL DEFAULT '-1',
    private String orderId;//varchar(32) NOT NULL COMMENT '订单编号',
    private Long userId;//bigint(20) NOT NULL DEFAULT '-1' COMMENT '用户id',
    private BigDecimal amount;//decimal(7,2) NOT NULL DEFAULT '0.00' COMMENT '订单总金额',
    private Integer marketId;//int(11) NOT NULL DEFAULT '0' COMMENT '市场id',
    private Integer payMethod;//int(11) NOT NULL DEFAULT '0' COMMENT '支付方式0钱包，1微信，2支付宝，3银联',
    private Integer orderStatus;//tinyint(4) NOT NULL DEFAULT '0' COMMENT '订单状态-1已删除订单，0待支付，1取消订单，2已支付，3,待发货，4,已发货，5交易完成，6退款申请，7退款中，8退款失败，9退款成功',
    private String description;//varchar(256) NOT NULL DEFAULT '' COMMENT '订单描述',
    private Date updateTime;//datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    private Date payTime;//datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '付款时间',
    private Date createTime;//datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
    private String channelSequence;//varchar(64) NOT NULL DEFAULT '' COMMENT '通道返回订单号',
    private String channelRetCode;//varchar(64) NOT NULL DEFAULT '' COMMENT '通道返回码',
    private String channelResponse;//text NOT NULL COMMENT '通道返回数据',
    private String refundSequence;//varchar(64) NOT NULL DEFAULT '' COMMENT '退款单号',
    private String channelRefundSequence;//varchar(64) NOT NULL DEFAULT '' COMMENT '退款渠道方单号',
    private String child_merchant_no;//子商户号

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId == null ? 0 : shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getOrderId() {
        return orderId == null ? "" : orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId== null ? 0 :userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount== null ?new BigDecimal(0) :amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getMarketId() {
        return marketId== null ? 0 :marketId;
    }

    public void setMarketId(Integer marketId) {
        this.marketId = marketId;
    }

    public Integer getPayMethod() {
        return payMethod== null ? 0 :payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public Integer getOrderStatus() {
        return orderStatus== null ? 0 :orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDescription() {
        return description== null ? "" :description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getChannelSequence() {
        return channelSequence== null ? "" :channelSequence;
    }

    public void setChannelSequence(String channelSequence) {
        this.channelSequence = channelSequence;
    }

    public String getChannelRetCode() {
        return channelRetCode== null ? "" :channelRetCode;
    }

    public void setChannelRetCode(String channelRetCode) {
        this.channelRetCode = channelRetCode;
    }

    public String getChannelResponse() {
        return channelResponse== null ? "" :channelResponse;
    }

    public void setChannelResponse(String channelResponse) {
        this.channelResponse = channelResponse;
    }

    public String getRefundSequence() {
        return refundSequence== null ? "" :refundSequence;
    }

    public void setRefundSequence(String refundSequence) {
        this.refundSequence = refundSequence;
    }

    public String getChannelRefundSequence() {
        return channelRefundSequence== null ? "" :channelRefundSequence;
    }

    public void setChannelRefundSequence(String channelRefundSequence) {
        this.channelRefundSequence = channelRefundSequence;
    }

    public String getChild_merchant_no() {
        return child_merchant_no;
    }

    public void setChild_merchant_no(String child_merchant_no) {
        this.child_merchant_no = child_merchant_no;
    }

    @Override
    public String toString() {
        return "OrderVO{" +
                "id=" + id +
                ", shopId=" + shopId +
                ", orderId='" + orderId + '\'' +
                ", userId=" + userId +
                ", amount=" + amount +
                ", marketId=" + marketId +
                ", payMethod=" + payMethod +
                ", orderStatus=" + orderStatus +
                ", description='" + description + '\'' +
                ", updateTime=" + updateTime +
                ", payTime=" + payTime +
                ", createTime=" + createTime +
                ", channelSequence='" + channelSequence + '\'' +
                ", channelRetCode='" + channelRetCode + '\'' +
                ", channelResponse='" + channelResponse + '\'' +
                ", refundSequence='" + refundSequence + '\'' +
                ", channelRefundSequence='" + channelRefundSequence + '\'' +
                ", child_merchant_no='" + child_merchant_no + '\'' +
                '}';
    }
}
