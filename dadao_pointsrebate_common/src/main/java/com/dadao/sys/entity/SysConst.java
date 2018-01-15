package com.dadao.sys.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SysConst {
    
    private Integer constId;    //unsigned NOT NULL AUTO_INCREMENT COMMENT '主键，自动递增'

    private String name;    //`name` varchar(255) DEFAULT NULL COMMENT '常量名'

    private BigDecimal value;   //`value` decimal(10,3) DEFAULT NULL COMMENT '常量值'

    private String description; //`description` varchar(255) DEFAULT NULL COMMENT '描述'

    private Date updateTime;    //`updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

    private Date createTime;    //`createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP

    private Integer status; //`status` int(10) unsigned DEFAULT '1' COMMENT '0无效、1有效'

    public Integer getConstId() {
        return constId;
    }

    public void setConstId(Integer constId) {
        this.constId = constId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
}