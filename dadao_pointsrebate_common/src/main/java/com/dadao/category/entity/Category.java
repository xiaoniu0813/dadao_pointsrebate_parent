package com.dadao.category.entity;

import com.dadao.utils.Page;

/**
 * Created by yangrui on 2017/7/16.
 */
public class Category extends Page {
    private Integer categoryId; //int(11) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
    private String categoryName; //varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '分类名称',
    private Integer sequence; //int(11) NOT NULL DEFAULT '0' COMMENT '排序字段',
    private Integer parentId; //int(11) NOT NULL DEFAULT '-1' COMMENT '父id',
    private String picture; //varchar(150) CHARACTER SET utf8 NOT NULL COMMENT '分类图标',
    private Integer type; //int(11) NOT NULL DEFAULT '0' COMMENT '分类类型',
    private Integer status; //int(11) NOT NULL DEFAULT '1' COMMENT '0.无效，1,有效',

    public Integer getCategoryId() {
        return categoryId==null?0:categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getSequence() {
        return sequence==null?0:sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status==null?0:status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", sequence=" + sequence +
                ", parentId=" + parentId +
                ", picture='" + picture + '\'' +
                ", type=" + type +
                ", status=" + status +
                '}';
    }
}
