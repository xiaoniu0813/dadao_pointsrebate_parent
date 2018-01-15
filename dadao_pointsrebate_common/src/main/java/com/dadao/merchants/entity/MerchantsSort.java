package com.dadao.merchants.entity;

/**
 * 商户分类
 *
 * @auther NFY niufuyang
 * @create 2017-11-9
 */
public class MerchantsSort {

    private Long id;//bigint(10) NOT NULL AUTO_INCREMENT COMMENT '无意义代理主键',
    private String sortCode;//varchar(20) NOT NULL DEFAULT '-1' COMMENT '分类编码',
    private String sortName;//varchar(200) NOT NULL DEFAULT '-1' COMMENT '分类名',
    private Long parentId;//bigint(10) NOT NULL DEFAULT '-1' COMMENT '父id',
    private Integer type;//int(11) NOT NULL DEFAULT '-1' COMMENT '分类类型(0一级分类、1二级分类)',

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MerchantsSort{" +
                "id=" + id +
                ", sortCode='" + sortCode + '\'' +
                ", sortName='" + sortName + '\'' +
                ", parentId=" + parentId +
                ", type=" + type +
                '}';
    }
}
