package com.dadao.businessreg.entity;


import com.dadao.merchants.entity.MerchantsInfo;

/**
 * @Author GUOYU 2017/12/11
 */
public class BusinessApply extends MerchantsInfo {

    private Long userId;// `userId` bigint(20) NOT NULL COMMENT '商户id',
    private Integer integralRate;//`regInfo` varchar(255) DEFAULT '' COMMENT '商户申请返回信息',
    private String regInfo;//`regInfo` varchar(255) DEFAULT '' COMMENT '商户申请返回信息',商户申请返回信息 平台返回商户信息、第三方支付返回的信息
    private Integer status;//`status` int(11) DEFAULT NULL COMMENT '状态 0待审核 1审核中 2审核成功 3审核失败，4审核回退',
    private String token;//用户token
    private Long yid;//对应gt_merchants_info中的id

    private String bankProvinceCode;//开户省编码
    private String bankCityCode;//开户市编码

    public Long getYid() {
        return yid;
    }

    public void setYid(Long yid) {
        this.yid = yid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getIntegralRate() {
        return integralRate;
    }

    public void setIntegralRate(Integer integralRate) {
        this.integralRate = integralRate;
    }

    public String getRegInfo() {
        return regInfo;
    }

    public void setRegInfo(String regInfo) {
        this.regInfo = regInfo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBankProvinceCode() {
        return bankProvinceCode;
    }

    public void setBankProvinceCode(String bankProvinceCode) {
        this.bankProvinceCode = bankProvinceCode;
    }

    public String getBankCityCode() {
        return bankCityCode;
    }

    public void setBankCityCode(String bankCityCode) {
        this.bankCityCode = bankCityCode;
    }

    @Override
    public String toString() {
        return "BusinessApply{" +
                "userId=" + userId +
                ", integralRate=" + integralRate +
                ", regInfo='" + regInfo + '\'' +
                ", status=" + status +
                ", token='" + token + '\'' +
                ", yid=" + yid +
                ", bankProvinceCode='" + bankProvinceCode + '\'' +
                ", bankCityCode='" + bankCityCode + '\'' +
                '}';
    }
}
