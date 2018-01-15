package com.dadao.order.entity;

import com.dadao.utils.Page;

/**
 * 订单详情（订单与商品的中间表）
 *
 * @auther NFY niufuyang
 * @create 2017-12-19
 */
public class OrderDetailsPO extends Page{
    private Long id;
    private Long orderId;
    private Long goodsId;
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public String toString() {
        return "OrderDetailsPO{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", goodsId=" + goodsId +
                ", quantity=" + quantity +
                '}';
    }
}
