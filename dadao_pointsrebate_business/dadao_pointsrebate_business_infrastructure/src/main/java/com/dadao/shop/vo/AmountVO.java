package com.dadao.shop.vo;

import java.math.BigDecimal;

/**
 * @Author: yangrui
 * @Description: 商户端首页信息
 * @Date: 下午4:53 2017/7/30
 */
public class AmountVO {
    private Long shopId;
    private String shopName;
    private BigDecimal amount;  //今日订单总金额
    private Integer orderCount; //今日订单数
    private String subMerchantNo;//子商户号

    public String getSubMerchantNo() {
        return subMerchantNo==null?"":subMerchantNo;
    }

    public void setSubMerchantNo(String subMerchantNo) {
        this.subMerchantNo = subMerchantNo;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    @Override
    public String toString() {
        return "AmountVO{" +
                "shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", amount=" + amount +
                ", orderCount=" + orderCount +
                ", subMerchantNo='" + subMerchantNo + '\'' +
                '}';
    }
}
