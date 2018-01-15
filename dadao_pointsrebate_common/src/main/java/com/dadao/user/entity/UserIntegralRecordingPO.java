package com.dadao.user.entity;



import com.dadao.utils.Page;

import java.math.BigDecimal;

public class UserIntegralRecordingPO extends Page{

    private Long IRid;//bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '积分记录表主键',
    private Long userId;//bigint(20) NOT NULL DEFAULT '-1' COMMENT '用户ID',
    private Integer direction;//int(11) NOT NULL DEFAULT '-1' COMMENT '发生方向(0增加、1减少)',
    private Long marketId;//bigint(20) NOT NULL DEFAULT '-1' COMMENT '市场表ID',
    private Long objectId;//bigint(20) NOT NULL DEFAULT '-1' COMMENT '对象ID(如果是增加积分该字段就是订单表ID，如果是减少积分该字段就是返现记录表ID)',
    private BigDecimal integral;//decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '积分',
    private String createTime;//datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    private Integer marketGrade;//市场等级
    private String integralStartTime;//消费开始
    private String integralEndTime;//消费结束
    private Integer status;//状态‘0.交易、1.退款、2.返利、3.商户积分’
    private String description;//描述
    private Integer pageNum;//当前页

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Long getIRid() {
        return IRid;
    }

    public void setIRid(Long IRid) {
        this.IRid = IRid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Long getMarketId() {
        return marketId;
    }

    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public BigDecimal getIntegral() {
        return integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getMarketGrade() {
        return marketGrade;
    }

    public void setMarketGrade(Integer marketGrade) {
        this.marketGrade = marketGrade;
    }

    public String getIntegralStartTime() {
        return integralStartTime;
    }

    public void setIntegralStartTime(String integralStartTime) {
        this.integralStartTime = integralStartTime;
    }

    public String getIntegralEndTime() {
        return integralEndTime;
    }

    public void setIntegralEndTime(String integralEndTime) {
        this.integralEndTime = integralEndTime;
    }

    @Override
    public String toString() {
        return "UserIntegralRecordingPO{" +
                "IRid=" + IRid +
                ", userId=" + userId +
                ", direction=" + direction +
                ", marketId=" + marketId +
                ", objectId=" + objectId +
                ", integral=" + integral +
                ", createTime='" + createTime + '\'' +
                ", marketGrade=" + marketGrade +
                ", integralStartTime='" + integralStartTime + '\'' +
                ", integralEndTime='" + integralEndTime + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", pageNum=" + pageNum +
                '}';
    }
}
