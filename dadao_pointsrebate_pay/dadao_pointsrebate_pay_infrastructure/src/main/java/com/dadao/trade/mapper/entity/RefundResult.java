package com.dadao.trade.mapper.entity;

/**
 * Created by YunQiang on 2017/11/24
 * @author YunQiang
 * @desription 退款结果
 */
public class RefundResult {

    /**
     *系统商或者平台商商编，如果是单商户，和收单商户商编保持一致
     */
    private String parentMerchantNo;

    /**
     *收单商户商编
     */
    private String merchantNo;

    /**
     *商户订单号
     */
    private String orderId;

    /**
     *易宝内部生成唯一订单流水号
     */
    private String uniqueOrderNo;

    /**
     *订单金额，下单请求金额
     */
    private String orderAmount;

    /**
     *剩余可退款金额， 单位：元，两位小数，最低0.01
     */
    private String residualAmount;

    /**
     *退款金额
     */
    private String refundAmount;

    /**
     *商户退款请求号
     */
    private String refundRequestId;

    /**
     *易宝唯一退款请求号
     */
    private String uniqueRefundNo;

    /**
     *订单退款说明
     */
    private String description;

    /**
     *退款请求时间
     */
    private String refundRequestDate;

    /**
     *退款状态：
     *  REJECT：退款拒绝
     *  PROCESSING：处理中
     *  SUSPEND：退款中断
     *  SUCCESS：退款成功
     *  CANCEL：退款撤销
     */
    private String status;

    /**
     *  退款成功时间（只有退款成功时回传）
     */
    private String refundSuccessDate;

    /**
     *  退款失败原因（只有退款失败时回传）
     */
    private String errorMessage;


    public String getParentMerchantNo() {
        return parentMerchantNo;
    }

    public void setParentMerchantNo(String parentMerchantNo) {
        this.parentMerchantNo = parentMerchantNo;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
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

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getResidualAmount() {
        return residualAmount;
    }

    public void setResidualAmount(String residualAmount) {
        this.residualAmount = residualAmount;
    }

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundRequestId() {
        return refundRequestId;
    }

    public void setRefundRequestId(String refundRequestId) {
        this.refundRequestId = refundRequestId;
    }

    public String getUniqueRefundNo() {
        return uniqueRefundNo;
    }

    public void setUniqueRefundNo(String uniqueRefundNo) {
        this.uniqueRefundNo = uniqueRefundNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRefundRequestDate() {
        return refundRequestDate;
    }

    public void setRefundRequestDate(String refundRequestDate) {
        this.refundRequestDate = refundRequestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRefundSuccessDate() {
        return refundSuccessDate;
    }

    public void setRefundSuccessDate(String refundSuccessDate) {
        this.refundSuccessDate = refundSuccessDate;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
