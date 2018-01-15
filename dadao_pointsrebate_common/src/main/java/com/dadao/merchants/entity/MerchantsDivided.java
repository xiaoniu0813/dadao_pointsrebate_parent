package com.dadao.merchants.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商户分账明细
 *
 * @auther NFY niufuyang
 * @create 2017-11-22
 */
public class MerchantsDivided {
    private Long id;//bigint(11) NOT NULL COMMENT '无意义代理主键',
    private String divideRequestId;//varchar(200) NOT NULL DEFAULT '-1' COMMENT '商户分账请求号',
    private String orderId;//varchar(200) NOT NULL DEFAULT '-1' COMMENT '商户订单号',
    private String uniqueOrderNo;//varchar(200) NOT NULL DEFAULT '-1' COMMENT '易宝统一订单号',
    private String contractNo;//varchar(200) NOT NULL DEFAULT '-1' COMMENT '合同号',
    private String ledgerNo;//varchar(200) NOT NULL DEFAULT '-1' COMMENT '分账方编号',
    private String ledgerName;//varchar(200) NOT NULL DEFAULT '-1' COMMENT '分账方名称',
    private String status;//varchar(30) NOT NULL DEFAULT '-1' COMMENT '状态',
    private String divideDetail;//text NOT NULL COMMENT '分账详情',
    private BigDecimal amount;//decimal(16,0) NOT NULL DEFAULT '-1' COMMENT '分账金额',
    private String message;//易宝返回信息
    private Date createTime;//datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDivideRequestId() {
        return divideRequestId;
    }

    public void setDivideRequestId(String divideRequestId) {
        this.divideRequestId = divideRequestId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUniqueOrderNo() {
        return uniqueOrderNo;
    }

    public void setUniqueOrderNo(String uniqueOrderNo) {
        this.uniqueOrderNo = uniqueOrderNo;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getLedgerNo() {
        return ledgerNo;
    }

    public void setLedgerNo(String ledgerNo) {
        this.ledgerNo = ledgerNo;
    }

    public String getLedgerName() {
        return ledgerName;
    }

    public void setLedgerName(String ledgerName) {
        this.ledgerName = ledgerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDivideDetail() {
        return divideDetail;
    }

    public void setDivideDetail(String divideDetail) {
        this.divideDetail = divideDetail;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "MerchantsDivided{" +
                "id=" + id +
                ", divideRequestId='" + divideRequestId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", uniqueOrderNo='" + uniqueOrderNo + '\'' +
                ", contractNo='" + contractNo + '\'' +
                ", ledgerNo='" + ledgerNo + '\'' +
                ", ledgerName='" + ledgerName + '\'' +
                ", status='" + status + '\'' +
                ", divideDetail='" + divideDetail + '\'' +
                ", amount=" + amount +
                ", message='" + message + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
