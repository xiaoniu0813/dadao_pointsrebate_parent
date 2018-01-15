package com.dadao.shop.entity;


import com.dadao.utils.Page;

import java.util.Date;

/**
 * 商户表
 * Created by yangrui on 2017/7/16.
 */
public class Shop extends Page {
    private Long shopId; //bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
    private String shopName; //varchar(64) NOT NULL DEFAULT '' COMMENT '用户昵称',
    private String shopDescription; //text COMMENT '店铺简介',
    private String phone; //varchar(20) NOT NULL DEFAULT '' COMMENT '店铺电话',
    private String picture; //varchar(128) NOT NULL DEFAULT '' COMMENT '店铺在用户端app中的详情页面展示的大图',
    private String verticalVersionPicture; //varchar(128) NOT NULL DEFAULT '' COMMENT '商铺标示图',
    private String markPicture; //varchar(128) NOT NULL DEFAULT '' COMMENT '用以用户APP附近旺铺处展示',
    private String longitude; //varchar(20) NOT NULL DEFAULT '' COMMENT '经度',
    private String latitude; //varchar(20) NOT NULL DEFAULT '' COMMENT '纬度',
    private String address; //varchar(512) NOT NULL DEFAULT '' COMMENT '地址',
    private Integer integralRate; //int(11) NOT NULL DEFAULT '0' COMMENT '佣金率',
    private Integer commissionRate; //int(11) NOT NULL DEFAULT '0' COMMENT '佣金率',
    private String businessStartTime; //datetime DEFAULT NULL COMMENT '营业开始时间',
    private String businessEndTime; //datetime DEFAULT NULL COMMENT '营业结束时间',
    private Integer perCapitaConsumption; //int(11) NOT NULL DEFAULT '50' COMMENT '人均消费',
    private Long fk_user_id;
    private String tags; //varchar(512) NOT NULL DEFAULT '' COMMENT '标签，多个标签空格分割',
    private Date createTime; //datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    private Integer categoryId;//分类ID
    private Integer categoryType;//分类类型
    private Integer scope = 5;//范围，默认5km.
    private String subMerchantNo;//子商户号

    public String getSubMerchantNo() {
        return subMerchantNo==null?"":subMerchantNo;
    }

    public void setSubMerchantNo(String subMerchantNo) {
        this.subMerchantNo = subMerchantNo;
    }

    public Integer getCategoryType() {
        return categoryType==null?0:categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public Integer getCategoryId() {
        return categoryId==null?0:categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getScope() {
        return scope;
    }

    public void setScope(Integer scope) {
        this.scope = scope;
    }

    public Long getShopId() {
        return shopId == null ? 0 : shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName == null ? "" : shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getPhone() {
        return phone == null ? "" : phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPicture() {
        return picture == null ? "" : picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getVerticalVersionPicture() {
        return verticalVersionPicture == null ? "" : verticalVersionPicture;
    }

    public void setVerticalVersionPicture(String verticalVersionPicture) {
        this.verticalVersionPicture = verticalVersionPicture;
    }

    public String getMarkPicture() {
        return markPicture == null ? "" : markPicture;
    }

    public void setMarkPicture(String markPicture) {
        this.markPicture = markPicture;
    }

    public String getLongitude() {
        return longitude == null ? "" : longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude == null ? "" : latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address == null ? "" : address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getIntegralRate() {
        return integralRate == null ? -2 : integralRate;
    }

    public void setIntegralRate(Integer integralRate) {
        this.integralRate = integralRate;
    }

    public Integer getCommissionRate() {
        return commissionRate == null ? -2 : commissionRate;
    }

    public void setCommissionRate(Integer commissionRate) {
        this.commissionRate = commissionRate;
    }

    public String getBusinessStartTime() {
        return businessStartTime;
    }

    public void setBusinessStartTime(String businessStartTime) {
        this.businessStartTime = businessStartTime;
    }

    public String getBusinessEndTime() {
        return businessEndTime;
    }

    public void setBusinessEndTime(String businessEndTime) {
        this.businessEndTime = businessEndTime;
    }

    public String getTags() {
        return tags == null ? "" : tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPerCapitaConsumption() {
        return perCapitaConsumption == null ? 0 : perCapitaConsumption;
    }

    public void setPerCapitaConsumption(Integer perCapitaConsumption) {
        this.perCapitaConsumption = perCapitaConsumption;
    }


    public Long getFk_user_id() {
        return fk_user_id;
    }

    public void setFk_user_id(Long fk_user_id) {
        this.fk_user_id = fk_user_id;
    }

    public String getShopDescription() {
        return shopDescription == null ? "" : shopDescription;
    }

    public void setShopDescription(String shopDescription) {
        this.shopDescription = shopDescription;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopId=" + shopId +
                ", shopName='" + shopName + '\'' +
                ", shopDescription='" + shopDescription + '\'' +
                ", phone='" + phone + '\'' +
                ", picture='" + picture + '\'' +
                ", verticalVersionPicture='" + verticalVersionPicture + '\'' +
                ", markPicture='" + markPicture + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", address='" + address + '\'' +
                ", integralRate=" + integralRate +
                ", commissionRate=" + commissionRate +
                ", businessStartTime='" + businessStartTime + '\'' +
                ", businessEndTime='" + businessEndTime + '\'' +
                ", perCapitaConsumption=" + perCapitaConsumption +
                ", fk_user_id=" + fk_user_id +
                ", tags='" + tags + '\'' +
                ", createTime=" + createTime +
                ", categoryId=" + categoryId +
                ", categoryType=" + categoryType +
                ", scope=" + scope +
                ", subMerchantNo='" + subMerchantNo + '\'' +
                '}';
    }
}
