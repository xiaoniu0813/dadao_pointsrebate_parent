package com.dadao.capitalpool.entity;

import java.math.BigDecimal;

/**
 * 资金池记录
 *
 * @auther NFY niufuyang
 * @create 2017-8-24
 */
public class CapitalpoolRecordVO {
    private Long id;//bigint(20) NOT NULL AUTO_INCREMENT COMMENT '无意义代理主键',
    private Long objectId;//bigint(20) NOT NULL DEFAULT '-1' COMMENT 'orderId/recordId（订单表ID/返现记录表ID）',
    private Integer status;//int(11) NOT NULL DEFAULT '0' COMMENT '状态（0交易成功，1退款，2扣除平台成本，3扣除返利）',
    private BigDecimal money;//发生金额
    private Long marketId;//bigint(20) NOT NULL DEFAULT '-1' COMMENT '市场表ID',
    private String description;//varchar(220) NOT NULL DEFAULT '' COMMENT '描述',
    private String createTime;//datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',

    public Long getId() {
        return id==null?0:id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getObjectId() {
        return objectId==null?0:objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Integer getStatus() {
        return status==null?0:status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getMoney() {
        return money==null?new BigDecimal("0"):money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Long getMarketId() {
        return marketId==null?0:marketId;
    }

    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }

    public String getDescription() {
        return description==null?"":description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateTime() {
        return createTime==null?"":createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "CapitalpoolRecordVO{" +
                "id=" + id +
                ", objectId=" + objectId +
                ", status=" + status +
                ", money=" + money +
                ", marketId=" + marketId +
                ", description='" + description + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
