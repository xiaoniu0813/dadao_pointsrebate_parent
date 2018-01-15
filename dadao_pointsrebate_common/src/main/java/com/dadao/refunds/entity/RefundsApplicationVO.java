package com.dadao.refunds.entity;

import java.util.Date;

/**
 * Created by YunQiang on 2017/8/9
 */
public class RefundsApplicationVO {

    private Long refundsId; //bigint(20) NOT NULL AUTO_INCREMENT COMMENT '无意义代理主键',
    private Long userId; //bigint(20) NOT NULL DEFAULT '-1' COMMENT '用户ID',
    private Long orderId; //bigint(20) NOT NULL DEFAULT '-1' COMMENT '订单表ID',
    private Integer status; //int(11) NOT NULL COMMENT '状态（0处理中、1商家审核通过、2平台处理中、3审核通过、4审核未通过）',
    private String processDetails; //varchar(120) NOT NULL DEFAULT '' COMMENT '退款流程明细（例如：处理中-->商家审核通过-->平台处理中）',
    private String userDescription; //varchar(220) NOT NULL DEFAULT '' COMMENT '用户申请退款描述',
    private String shopDescription; //varchar(220) NOT NULL DEFAULT '' COMMENT '审核描述（商家填写）',
    private String platformDescription; //varchar(220) NOT NULL DEFAULT '' COMMENT '审核描述（平台）',
    private Date createTime; //datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',

    public Long getRefundsId() {
        return refundsId == null ? -2 : refundsId;
    }

    public void setRefundsId(Long refundsId) {
        this.refundsId = refundsId;
    }

    public Long getUserId() {
        return userId == null ? -2 : userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId == null ? -2 : orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return status == null ? -2 : status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getProcessDetails() {
        return processDetails == null ? "" : processDetails;
    }

    public void setProcessDetails(String processDetails) {
        this.processDetails = processDetails;
    }

    public String getUserDescription() {
        return userDescription == null ? "" : userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public String getShopDescription() {
        return shopDescription == null ? "" : shopDescription;
    }

    public void setShopDescription(String shopDescription) {
        this.shopDescription = shopDescription;
    }

    public String getPlatformDescription() {
        return platformDescription == null ? "" : platformDescription;
    }

    public void setPlatformDescription(String platformDescription) {
        this.platformDescription = platformDescription;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
