package com.dadao.user.entity;

import java.math.BigDecimal;

/**
 * 用户积分记录表
 *
 * @auther NFY niufuyang
 * @create 2017-08-02
 */
public class UserIntegralRecordingVO {
    private Long IRid;//bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '积分记录表主键',
    private Long userId;//bigint(20) NOT NULL DEFAULT '-1' COMMENT '用户ID',
    private Integer direction;//int(11) NOT NULL DEFAULT '-1' COMMENT '发生方向(0增加、1减少)',
    private Long marketId;//bigint(20) NOT NULL DEFAULT '-1' COMMENT '市场表ID',
    private Long objectId;//bigint(20) NOT NULL DEFAULT '-1' COMMENT '对象ID(如果是增加积分该字段就是订单表ID，如果是减少积分该字段就是返现记录表ID)',
    private BigDecimal integral;//decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '积分',
    private Integer status;//状态‘0.交易、1.退款、2.返利、3.商户积分’
    private String description;//描述
    private String createTime;//datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',

    public Integer getStatus() {
        return status==null?0:status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description==null?"":description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getIRid() {
        return IRid==null?0:IRid;
    }

    public void setIRid(Long IRid) {
        this.IRid = IRid;
    }

    public Long getUserId() {
        return userId==null?0:userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getDirection() {
        return direction==null?0:direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Long getMarketId() {
        return marketId==null?0:marketId;
    }

    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }

    public Long getObjectId() {
        return objectId==null?0:objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public BigDecimal getIntegral() {
        return integral==null?new BigDecimal("0"):integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }

    public String getCreateTime() {
        return createTime==null?"":createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserIntegralRecordingVO{" +
                "IRid=" + IRid +
                ", userId=" + userId +
                ", direction=" + direction +
                ", marketId=" + marketId +
                ", objectId=" + objectId +
                ", integral=" + integral +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
