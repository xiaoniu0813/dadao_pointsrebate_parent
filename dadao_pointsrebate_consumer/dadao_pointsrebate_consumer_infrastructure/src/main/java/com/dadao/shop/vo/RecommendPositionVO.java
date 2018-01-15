package com.dadao.shop.vo;

import com.dadao.GeneralConstants;

public class RecommendPositionVO {
    private Integer recommendId; //bigint(20) NOT NULL AUTO_INCREMENT COMMENT '推荐位Id',
    private String picture; //varchar(128) NOT NULL DEFAULT '' COMMENT '推荐位图片',
    private String description; //varchar(256) NOT NULL DEFAULT '' COMMENT '描述',
    private Integer type; //int(11) NOT NULL DEFAULT '-1' COMMENT '0首页轮播图;1推荐位',
    private Integer sequence; //int(11) NOT NULL DEFAULT '0' COMMENT '排序',
    private Integer status; //int(11) NOT NULL DEFAULT '1' COMMENT '0无效，1有效',
    private String createTime; //datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',

    public Integer getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(Integer recommendId) {
        this.recommendId = recommendId;
    }

    public String getPicture() {
        return picture == null ? "" : picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description == null ? "" : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType() {
        return type == null ? GeneralConstants.APP_INT : type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSequence() {
        return sequence == null ? GeneralConstants.APP_INT : sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getStatus() {
        return status == null ? GeneralConstants.APP_INT : status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime == null ? "" : createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "RecommendPositionVO{" +
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
