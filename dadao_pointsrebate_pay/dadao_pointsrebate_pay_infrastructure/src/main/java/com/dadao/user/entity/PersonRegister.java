package com.dadao.user.entity;

/**
 * Created by YunQiang on 2017/11/9
 * @author YunQiang
 */
public class PersonRegister {
    //父商编
    private String parentMerchantNo;
    //入网请求号
    private String requestNo;
    //法人姓名
    private String legalName;
    //法人身份证号
    private String legalIdCard;
    //商户法人手String 是128 为方便联系请认真填写真实信息机
    private String merLegalPhone;
    //商户法人邮箱
    private String merLegalEmail;
    //商户一级分类编码
    private String merLevel1No;
    //商户二级分类编码
    private String merLevel2No;
    //商户经营地址所在省
    private String merProvince;
    //商户经营地址所在市
    private String merCity;
    //商户经营地址所在区
    private String merDistrict;
    //商户经营地址所在具体地址
    private String merAddress;
    //商户经营范围
    private String merScope;
    //银行账户
    private String cardNo;
    //开户银行总行编码
    private String headBankCode;
    //开户行支行编码
    private String bankCode	;
    //授权类型
    private String merAuthorizeType;
    //开户省
    private String bankProvince;
    //开户市
    private String bankCity;
    //开通产品
    private String productInfo	;
    //资质影印件
    private String fileInfo	;
    //商户回调地址
    private String notifyUrl;
    //业务功能
    private String businessFunction;

    public String getParentMerchantNo() {
        return parentMerchantNo;
    }

    public void setParentMerchantNo(String parentMerchantNo) {
        this.parentMerchantNo = parentMerchantNo;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getLegalIdCard() {
        return legalIdCard;
    }

    public void setLegalIdCard(String legalIdCard) {
        this.legalIdCard = legalIdCard;
    }

    public String getMerLegalPhone() {
        return merLegalPhone;
    }

    public void setMerLegalPhone(String merLegalPhone) {
        this.merLegalPhone = merLegalPhone;
    }

    public String getMerLegalEmail() {
        return merLegalEmail;
    }

    public void setMerLegalEmail(String merLegalEmail) {
        this.merLegalEmail = merLegalEmail;
    }

    public String getMerLevel1No() {
        return merLevel1No;
    }

    public void setMerLevel1No(String merLevel1No) {
        this.merLevel1No = merLevel1No;
    }

    public String getMerLevel2No() {
        return merLevel2No;
    }

    public void setMerLevel2No(String merLevel2No) {
        this.merLevel2No = merLevel2No;
    }

    public String getMerProvince() {
        return merProvince;
    }

    public void setMerProvince(String merProvince) {
        this.merProvince = merProvince;
    }

    public String getMerCity() {
        return merCity;
    }

    public void setMerCity(String merCity) {
        this.merCity = merCity;
    }

    public String getMerDistrict() {
        return merDistrict;
    }

    public void setMerDistrict(String merDistrict) {
        this.merDistrict = merDistrict;
    }

    public String getMerAddress() {
        return merAddress;
    }

    public void setMerAddress(String merAddress) {
        this.merAddress = merAddress;
    }

    public String getMerScope() {
        return merScope;
    }

    public void setMerScope(String merScope) {
        this.merScope = merScope;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getHeadBankCode() {
        return headBankCode;
    }

    public void setHeadBankCode(String headBankCode) {
        this.headBankCode = headBankCode;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getMerAuthorizeType() {
        return merAuthorizeType;
    }

    public void setMerAuthorizeType(String merAuthorizeType) {
        this.merAuthorizeType = merAuthorizeType;
    }

    public String getBankProvince() {
        return bankProvince;
    }

    public void setBankProvince(String bankProvince) {
        this.bankProvince = bankProvince;
    }

    public String getBankCity() {
        return bankCity;
    }

    public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public String getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(String fileInfo) {
        this.fileInfo = fileInfo;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getBusinessFunction() {
        return businessFunction;
    }

    public void setBusinessFunction(String businessFunction) {
        this.businessFunction = businessFunction;
    }

    @Override
    public String toString() {
        return "PersonRegister{" +
                "parentMerchantNo='" + parentMerchantNo + '\'' +
                ", requestNo='" + requestNo + '\'' +
                ", legalName='" + legalName + '\'' +
                ", legalIdCard='" + legalIdCard + '\'' +
                ", merLegalPhone='" + merLegalPhone + '\'' +
                ", merLegalEmail='" + merLegalEmail + '\'' +
                ", merLevel1No='" + merLevel1No + '\'' +
                ", merLevel2No='" + merLevel2No + '\'' +
                ", merProvince='" + merProvince + '\'' +
                ", merCity='" + merCity + '\'' +
                ", merDistrict='" + merDistrict + '\'' +
                ", merAddress='" + merAddress + '\'' +
                ", merScope='" + merScope + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", headBankCode='" + headBankCode + '\'' +
                ", bankCode='" + bankCode + '\'' +
                ", merAuthorizeType='" + merAuthorizeType + '\'' +
                ", bankProvince='" + bankProvince + '\'' +
                ", bankCity='" + bankCity + '\'' +
                ", productInfo='" + productInfo + '\'' +
                ", fileInfo='" + fileInfo + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", businessFunction='" + businessFunction + '\'' +
                '}';
    }
}
