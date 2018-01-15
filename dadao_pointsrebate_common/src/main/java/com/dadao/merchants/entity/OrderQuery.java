package com.dadao.merchants.entity;

import java.util.List;

public class OrderQuery {

    private String parentMerchantNo;//主商户编号
    private String merchantNo;//商户编号
    private String orderId;//商户订单号
    private String uniqueOrderNo;//易包流水号
    private String hmac;//子商户签名
    private String code;//返回码
    private String message;//返回信息
    private String status;//订单状态 退款状态 批量查询
    private String orderAmount;//订单金额
    private String payAmount;//实付金额
    private String orderFee;//订单支付手续费
    private String requestDate;//请求时间
    private String paySuccessDate;//支付时间
    private String goodsParamExt;//商品名称
    private String memo;//自定义对账备注 退款拒绝原因
    private String openID;//公众号、
    private String unionID;//用户在微信下的唯一标识
    private String refundRequestId;//退款请求号
    private String uniqueRefundNo;//易宝统一退款号
    private String refundAmount;//退款金额
    private String descripiton;//订单退款说明
    private String refundRequestDate;//退款请求时间
    private String refundSuccessDate;//退款处理时间
    private String requestDateBegin;//请求开始时间
    private String requestDateEnd;//请求结束时间
    private String pageNo;//页码
    private String pageSize;//每页记录数
    private String totalRecords;//总记录数
    private List<Order> orderList;//订单集合
    private String startSettleDate;//结算处理开始日期
    private String endSettleDate;//结算处理结束日期
    private List<SettleRecord> settleRecordList;//结束历史数据
    private String divideRequestId;//商户分账请求号
    private String divideDetail;//分账详情
    private String goodsName;//货品名称
    private String goodsDesc;//货品描述


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

    public String getHmac() {
        return hmac;
    }

    public void setHmac(String hmac) {
        this.hmac = hmac;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getDescripiton() {
        return descripiton;
    }

    public void setDescripiton(String descripiton) {
        this.descripiton = descripiton;
    }

    public String getRefundRequestDate() {
        return refundRequestDate;
    }

    public void setRefundRequestDate(String refundRequestDate) {
        this.refundRequestDate = refundRequestDate;
    }

    public String getRefundSuccessDate() {
        return refundSuccessDate;
    }

    public void setRefundSuccessDate(String refundSuccessDate) {
        this.refundSuccessDate = refundSuccessDate;
    }

    public String getRequestDateBegin() {
        return requestDateBegin;
    }

    public void setRequestDateBegin(String requestDateBegin) {
        this.requestDateBegin = requestDateBegin;
    }

    public String getRequestDateEnd() {
        return requestDateEnd;
    }

    public void setRequestDateEnd(String requestDateEnd) {
        this.requestDateEnd = requestDateEnd;
    }

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(String totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public String getStartSettleDate() {
        return startSettleDate;
    }

    public void setStartSettleDate(String startSettleDate) {
        this.startSettleDate = startSettleDate;
    }

    public String getEndSettleDate() {
        return endSettleDate;
    }

    public void setEndSettleDate(String endSettleDate) {
        this.endSettleDate = endSettleDate;
    }

    public List<SettleRecord> getSettleRecordList() {
        return settleRecordList;
    }

    public void setSettleRecordList(List<SettleRecord> settleRecordList) {
        this.settleRecordList = settleRecordList;
    }

    public String getDivideRequestId() {
        return divideRequestId;
    }

    public void setDivideRequestId(String divideRequestId) {
        this.divideRequestId = divideRequestId;
    }

    public String getDivideDetail() {
        return divideDetail;
    }

    public void setDivideDetail(String divideDetail) {
        this.divideDetail = divideDetail;
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

    @Override
    public String toString() {
        return "OrderQuery{" +
                "parentMerchantNo='" + parentMerchantNo + '\'' +
                ", merchantNo='" + merchantNo + '\'' +
                ", orderId='" + orderId + '\'' +
                ", uniqueOrderNo='" + uniqueOrderNo + '\'' +
                ", hmac='" + hmac + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
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
                ", refundRequestId='" + refundRequestId + '\'' +
                ", uniqueRefundNo='" + uniqueRefundNo + '\'' +
                ", refundAmount='" + refundAmount + '\'' +
                ", descripiton='" + descripiton + '\'' +
                ", refundRequestDate='" + refundRequestDate + '\'' +
                ", refundSuccessDate='" + refundSuccessDate + '\'' +
                ", requestDateBegin='" + requestDateBegin + '\'' +
                ", requestDateEnd='" + requestDateEnd + '\'' +
                ", pageNo='" + pageNo + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", totalRecords='" + totalRecords + '\'' +
                ", orderList=" + orderList +
                ", startSettleDate='" + startSettleDate + '\'' +
                ", endSettleDate='" + endSettleDate + '\'' +
                ", settleRecordList=" + settleRecordList +
                ", divideRequestId='" + divideRequestId + '\'' +
                ", divideDetail='" + divideDetail + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsDesc='" + goodsDesc + '\'' +
                '}';
    }
}
