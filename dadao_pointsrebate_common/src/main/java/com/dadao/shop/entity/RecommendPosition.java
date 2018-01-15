package com.dadao.shop.entity;

import com.dadao.utils.Page;

import java.util.Date;

/**
 * 推荐位
 * Created by yunqiang1 on 2017/7/16.
 */
public class RecommendPosition extends Page {
    private Integer recommendId; //bigint(20) NOT NULL AUTO_INCREMENT COMMENT '推荐位Id',
    private String picture; //varchar(128) NOT NULL DEFAULT '' COMMENT '推荐位图片',
    private String description; //varchar(256) NOT NULL DEFAULT '' COMMENT '描述',
    private Integer type; //int(11) NOT NULL DEFAULT '-1' COMMENT '0首页轮播图;1推荐位',
    private Integer sequence; //int(11) NOT NULL DEFAULT '0' COMMENT '排序',
    private Integer status; //int(11) NOT NULL DEFAULT '1' COMMENT '0无效，1有效',
    private Date createTime; //datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',

    public Integer getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(Integer recommendId) {
        this.recommendId = recommendId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "RecommendPosition{" +
                "recommendId=" + recommendId +
                ", picture='" + picture + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", sequence=" + sequence +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
