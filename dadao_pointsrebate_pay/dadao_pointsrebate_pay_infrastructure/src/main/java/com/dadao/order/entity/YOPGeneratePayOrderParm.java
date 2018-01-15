package com.dadao.order.entity;

/**
 * 易宝生成支付订单所需参数
 *
 * @auther NFY niufuyang
 * @create 2017-11-27
 */
public class YOPGeneratePayOrderParm {
    private String userNo;
    private String token;
    private String merchantNo;
    private String timeStamp;
    private String sign;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "YOPGeneratePayOrderParm{" +
                "userNo='" + userNo + '\'' +
                ", token='" + token + '\'' +
                ", merchantNo='" + merchantNo + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
