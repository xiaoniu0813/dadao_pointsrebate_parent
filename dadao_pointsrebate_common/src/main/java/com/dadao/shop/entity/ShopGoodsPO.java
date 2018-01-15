package com.dadao.shop.entity;

import com.dadao.utils.BasePage;
import com.dadao.utils.Page;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商铺商品表
 *
 * @auther NFY niufuyang
 * @create 2017-12-19
 */
public class ShopGoodsPO extends BasePage{

    private Long id;//bigint(20) NOT NULL AUTO_INCREMENT COMMENT '无意义代理主键',
    private String goodsName;//varchar(160) NOT NULL DEFAULT '-1' COMMENT '商品名',
    private String goodsImg;//varchar(160) NOT NULL DEFAULT '-1' COMMENT '商品图片',
    private BigDecimal goodsPrice;//decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '商品价格',
    private String description;//text COMMENT '描述',
    private String browseLinks;//varchar(160) NOT NULL DEFAULT '-1' COMMENT '浏览链接',
    private String browseKey;//varchar(160) NOT NULL DEFAULT '-1' COMMENT '浏览密钥',
    private Long shopId;//bigint(20) NOT NULL DEFAULT '-1' COMMENT '对应商铺ID',
    private Date createTime;//datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    private Integer status;//int(2) NOT NULL DEFAULT '-1' COMMENT '商品状态（0.审核中、1.正常、2.审核失败、3.删除）',
    private Date updateTime;//datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    private Integer payMode;//int(2) DEFAULT '0' COMMENT '付款方式（0.线下，1.线上）',

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrowseLinks() {
        return browseLinks;
    }

    public void setBrowseLinks(String browseLinks) {
        this.browseLinks = browseLinks;
    }

    public String getBrowseKey() {
        return browseKey;
    }

    public void setBrowseKey(String browseKey) {
        this.browseKey = browseKey;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getPayMode() {
        return payMode;
    }

    public void setPayMode(Integer payMode) {
        this.payMode = payMode;
    }

    @Override
    public String toString() {
        return "ShopGoodsPO{" +
                "id=" + id +
                ", goodsName='" + goodsName + '\'' +
                ", goodsImg='" + goodsImg + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", description='" + description + '\'' +
                ", browseLinks='" + browseLinks + '\'' +
                ", browseKey='" + browseKey + '\'' +
                ", shopId=" + shopId +
                ", createTime=" + createTime +
                ", status=" + status +
                ", updateTime=" + updateTime +
                ", payMode=" + payMode +
                '}';
    }
}
