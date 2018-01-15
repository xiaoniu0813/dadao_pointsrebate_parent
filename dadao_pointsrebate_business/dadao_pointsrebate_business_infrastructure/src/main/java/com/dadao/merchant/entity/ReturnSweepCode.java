package com.dadao.merchant.entity;

/**
 * 返回扫码所需entity
 *
 * @auther NFY niufuyang
 * @create 2017-9-29
 */
public class ReturnSweepCode {
    private String subMerchantNo;//子商户号
    private String merchantName;//商户名称
    private String shortName;//商户简称
    private String merchantAddress;//商户地址

    public String getSubMerchantNo() {
        return subMerchantNo;
    }

    public void setSubMerchantNo(String subMerchantNo) {
        this.subMerchantNo = subMerchantNo;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getMerchantAddress() {
        return merchantAddress;
    }

    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    @Override
    public String toString() {
        return "ReturnSweepCode{" +
                "subMerchantNo='" + subMerchantNo + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", merchantAddress='" + merchantAddress + '\'' +
                '}';
    }
}
