package com.dadao.order.entity;

/**
 * 易宝支付回调参数
 *
 * @auther NFY niufuyang
 * @create 2017-11-20
 */
public class YOPCallbackParameter {
    private String parentMerchantNo;//主商户编号
    private String merchantNo;//商户编号
    private String orderId;//商户订单号
    private String uniqueOrderNo;//易宝流水号
    private String status;//订单状态
    private String orderAmount;//订单金额
    private String payAmount;//实付金额
    private String requestDate;//请求时间
    private String paySuccessDate;//完成时间

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getPaySuccessDate() {
        return paySuccessDate;
    }

    public void setPaySuccessDate(String paySuccessDate) {
        this.paySuccessDate = paySuccessDate;
    }

    @Override
    public String toString() {
        return "YOPCallbackParameter{" +
                "parentMerchantNo='" + parentMerchantNo + '\'' +
                ", merchantNo='" + merchantNo + '\'' +
                ", orderId='" + orderId + '\'' +
                ", uniqueOrderNo='" + uniqueOrderNo + '\'' +
                ", status='" + status + '\'' +
                ", orderAmount='" + orderAmount + '\'' +
                ", payAmount='" + payAmount + '\'' +
                ", requestDate='" + requestDate + '\'' +
                ", paySuccessDate='" + paySuccessDate + '\'' +
                '}';
    }
}
