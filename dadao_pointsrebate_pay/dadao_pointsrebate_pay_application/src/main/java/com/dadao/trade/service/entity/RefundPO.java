package com.dadao.trade.service.entity;

/**
 * Created by YunQiang on 2017/11/21
 * @author YunQiang
 * @description 退款专用实体类
 */
public class RefundPO {

    /**
     * 主商户编号：
     * 系统商或者平台商商编，如果是单商户，和收单商户商编保持一致
     */
    private String parentMerchantNo;

    /**
     * 商户编号:
     * 收单商户商编
     */
    private String merchantNo;

    /**
     * 原商户订单号：
     * 需要退款订单的商户订单号，原商户订单号+退款请求号不可重复
     */
    private String orderId;

    /**
     *易宝流水号：
     * 易宝内部生成唯一订单流水号
     */
    private String uniqueOrderNo;

    /**
     *退款请求号：
     * 订单退款的请求号，原商户订单号+退款请求号不可重复
     */
    private String refundRequestId;

    /**
     *退款金额：
     * 单位：元，两位小数，最低0.01
     */
    private String refundAmount;

    /**
     *订单退款说明
     */
    private String description;

    /**
     * 自定义对账备注：
     * 商户可以自定义自身业务需要使用的字段：如对账时定义该订单应属的会计科目
     */
    private String memo;

    /**
     *子商户签名：
     * 所有以上带签名顺序的参数以key=value 的方式，
     * 以&分隔，以签名顺序为排序，拼接成一个字符串；
     * 使用子商户密钥查询接口查询到的子商户密钥做一次
     * SHA256 签名，生成HMAC
     */
    private String hmac;

    /**
     *退款服务器通知地址
     */
    private String notifyUrl;

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

    public String getRefundRequestId() {
        return refundRequestId;
    }

    public void setRefundRequestId(String refundRequestId) {
        this.refundRequestId = refundRequestId;
    }

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getHmac() {
        return hmac;
    }

    public void setHmac(String hmac) {
        this.hmac = hmac;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
