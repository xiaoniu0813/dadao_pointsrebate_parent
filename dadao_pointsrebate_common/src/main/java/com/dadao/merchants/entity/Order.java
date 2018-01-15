package com.dadao.merchants.entity;

public class Order {

    private String parentMerchantNo;//主商编
    private String merchantNo;//商户编号
    private String orderId;//商户订单号
    private String uniqueOrderNo;//易宝流水号
    private String status;//订单状态
    private String orderAmount;//订单金额
    private String payAmount;//实付金额
    private String orderFee;//订单支付手续费
    private String requestDate;//请求时间
    private String paySuccessDate;//支付时间
    private String goodsParamExt;//商品名称
    private String memo;//自定义对账备注
    private String openID;//公众号用户openid
    private String unionID;//用户在微信下的唯一标识
    private String goodsName;//商品名称
    private String goodsDesc;//商品描述
    private String merchantFee;//商户手续费
    private String customerFee;//用户手续费
    private String fundProcessType;//资金处理类型

    public String getMerchantFee() {
        return merchantFee;
    }

    public void setMerchantFee(String merchantFee) {
        this.merchantFee = merchantFee;
    }

    public String getCustomerFee() {
        return customerFee;
    }

    public void setCustomerFee(String customerFee) {
        this.customerFee = customerFee;
    }

    public String getFundProcessType() {
        return fundProcessType;
    }

    public void setFundProcessType(String fundProcessType) {
        this.fundProcessType = fundProcessType;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

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

    public String getOrderFee() {
        return orderFee;
    }

    public void setOrderFee(String orderFee) {
        this.orderFee = orderFee;
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

    public String getGoodsParamExt() {
        return goodsParamExt;
    }

    public void setGoodsParamExt(String goodsParamExt) {
        this.goodsParamExt = goodsParamExt;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    public String getUnionID() {
        return unionID;
    }

    public void setUnionID(String unionID) {
        this.unionID = unionID;
    }

    @Override
    public String toString() {
        return "Order{" +
                "parentMerchantNo='" + parentMerchantNo + '\'' +
                ", merchantNo='" + merchantNo + '\'' +
                ", orderId='" + orderId + '\'' +
                ", uniqueOrderNo='" + uniqueOrderNo + '\'' +
                ", status='" + status + '\'' +
                ", orderAmount='" + orderAmount + '\'' +
                ", payAmount='" + payAmount + '\'' +
                ", orderFee='" + orderFee + '\'' +
                ", requestDate='" + requestDate + '\'' +
                ", paySuccessDate='" + paySuccessDate + '\'' +
                ", goodsParamExt='" + goodsParamExt + '\'' +
                ", memo='" + memo + '\'' +
                ", openID='" + openID + '\'' +
                ", unionID='" + unionID + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsDesc='" + goodsDesc + '\'' +
                ", merchantFee='" + merchantFee + '\'' +
                ", customerFee='" + customerFee + '\'' +
                ", fundProcessType='" + fundProcessType + '\'' +
                '}';
    }
}
