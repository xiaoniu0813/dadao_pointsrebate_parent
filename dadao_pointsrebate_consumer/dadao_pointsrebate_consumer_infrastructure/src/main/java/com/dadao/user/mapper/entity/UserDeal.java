package com.dadao.user.mapper.entity;

import java.math.BigDecimal;
/**
 * @auther GUOYU 2017/12/04
 */
public class UserDeal {

    private String productName;//货物名称
    private String createTime;//获取交易时间
    private BigDecimal productIntegral;//获得积分
    private BigDecimal productPrice;//商品价格
    private Integer direction;//积分方向 0增加、1减少
    private Integer status;//'状态‘0.交易、1.退款、2.返利、3.商户积分’'

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getProductIntegral() {
        return productIntegral;
    }

    public void setProductIntegral(BigDecimal productIntegral) {
        this.productIntegral = productIntegral;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "UserDeal{" +
                "productName='" + productName + '\'' +
                ", createTime='" + createTime + '\'' +
                ", productIntegral=" + productIntegral +
                ", productPrice=" + productPrice +
                ", direction=" + direction +
                ", status=" + status +
                '}';
    }
}
