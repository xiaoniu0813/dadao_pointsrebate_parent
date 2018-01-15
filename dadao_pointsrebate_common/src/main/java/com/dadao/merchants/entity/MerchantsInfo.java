package com.dadao.merchants.entity;

import com.dadao.utils.BasePage;
import com.dadao.utils.Page;

/**
 * 商户信息（对接易宝支付）
 *
 * @auther NFY niufuyang
 * @create 2017-11-9
 */
public class MerchantsInfo extends BasePage{

    private Long id;//bigint(20) NOT NULL AUTO_INCREMENT COMMENT '无意义代理主键',
    private String merFullName;//varchar(255) NOT NULL DEFAULT '-1' COMMENT '商户全称',
    private String merShortName;//varchar(255) NOT NULL DEFAULT '-1' COMMENT '商户品牌名称/简称',
    private String merCertType;//int(2) NOT NULL DEFAULT '0' COMMENT '证件类型（CORP_CODE营业执照、UNI_CREDIT_CODE统一社会信用代码证）',
    private String merCertNo;//varchar(100) NOT NULL DEFAULT '-1' COMMENT '证件号',
    private String legalName;//varchar(30) NOT NULL DEFAULT '-1' COMMENT '法人姓名',
    private String legalIdCard;//varchar(30) NOT NULL DEFAULT '-1' COMMENT '法人身份证号',
    private String merLegalPhone;//varchar(15) NOT NULL DEFAULT '-1' COMMENT '商户/法人手机',
    private String merLegalEmail;//varchar(128) NOT NULL DEFAULT '-1' COMMENT '商户/法人邮箱',
    private String merLevel1No;//bigint(20) NOT NULL DEFAULT '-1' COMMENT '商户一级分类编码',
    private String merLevel2No;//bigint(20) NOT NULL DEFAULT '-1' COMMENT '商户二级分类编码',
    private String merProvince;//varchar(100) NOT NULL DEFAULT '-1' COMMENT '商户经营地址所在省 ',
    private String merCity;//varchar(100) NOT NULL DEFAULT '-1' COMMENT '商户经营地址所在市',
    private String merDistrict;//varchar(100) NOT NULL DEFAULT '-1' COMMENT '商户经营地址所在区',
    private String merAddress;//varchar(200) NOT NULL DEFAULT '-1' COMMENT '商户经营地址所在具体地址',
    private String merScope;//varchar(200) NOT NULL DEFAULT '-1' COMMENT '商户经营范围',
    private String merContactName;//varchar(200) NOT NULL DEFAULT '-1' COMMENT '商户联系人姓名',
    private String taxRegistCert;//varchar(200) NOT NULL DEFAULT '-1' COMMENT '税务登记证编号',
    private String accountLicense;//varchar(200) NOT NULL DEFAULT '-1' COMMENT '开户许可证 编号',
    private String orgCode;//varchar(200) NOT NULL DEFAULT '-1' COMMENT '组织机构代码证',
    private String isOrgCodeLong;//varchar(200) NOT NULL DEFAULT '-1' COMMENT '组织机构代码证是否长期有效',
    private String orgCodeExpiry;//datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '组织机构代理证有效期',
    private String cardNo;//varchar(200) NOT NULL DEFAULT '-1' COMMENT '银行账户',
    private String headBankCode;//varchar(200) NOT NULL DEFAULT '-1' COMMENT '开户银行总行编码',
    private String bankProvince;//varchar(200) NOT NULL DEFAULT '-1' COMMENT '开户省 ',
    private String bankCity;//varchar(200) NOT NULL DEFAULT '-1' COMMENT '开户市',
    private String bankCode;//varchar(200) NOT NULL DEFAULT '-1' COMMENT '开户银行编码 ',
    private String productInfo;//varchar(600) NOT NULL DEFAULT '-1' COMMENT '开通产品',
    private String fileInfo;//varchar(600) NOT NULL DEFAULT '-1' COMMENT '资质影印件',
    private String businessFunction;//varchar(600) NOT NULL DEFAULT '-1' COMMENT '业务功能',
    private String notifyUrl;//varchar(600) NOT NULL DEFAULT '-1' COMMENT '商户回调地址',
    private String merAuthorizeType;//varchar(50) NOT NULL DEFAULT '-1' COMMENT '授权类型',
    private String merType;//int(2) NOT NULL DEFAULT '0' COMMENT '商户类型（0个人、1个体、2企业）',
    private String createTime;//datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    private String requestNo;//`requestNo` varchar(64) NOT NULL DEFAULT '-1' COMMENT '入网请求号，字母加字符字符串组合',
    private String parentMerchantNo;//`parentMerchantNo` varchar(64) NOT NULL DEFAULT '-1' COMMENT '接口调用方所在易宝的商户编号',
    private String merContactPhone;//  `merContactPhone` varchar(128) NOT NULL DEFAULT '-1' COMMENT '商户联系人手机',
    private String merchantNo;//该入网商户所在易宝的商户商编
    private String externalId;//返回该条请求易宝的流水号
    private String agreementContent;//易宝所生成电子协议内容，需由系统商展示 给注册商户，并由注册商户同意确认。
    private String merContactEmail;//商户联系人邮箱
    private String returnCode;//`returnCode` varchar(64) NOT NULL DEFAULT '-1' COMMENT '请求响应码',
    private String returnMsg;//`returnMsg` varchar(64) NOT NULL DEFAULT '-1' COMMENT '响应信息',
    private String commissionRate;  //佣金率（未存数据库）
    private String merNetInStatus;//`merNetInStatus` varchar(64) DEFAULT '商户入网状态',
    private String remark;//`remark` text COMMENT '回调接口备注',
    private String paramerchanno;

    private String bankCityCode; //开户市编码
    private String bankProvinceCode;//开户省编码
    private String merDistrictCode;//商户经营所在区编码
    private String merCityCode;//商户经营地所在市编码
    private String merProvinceCode;//商户经营地所在省
    private String merLevel1NoCode;//商户一级分类编码
    private String merLevel2NoCode;//商户二级分类编码
    private String headBank;//总行编码

    public String getHeadBank() {
        return headBank;
    }

    public void setHeadBank(String headBank) {
        this.headBank = headBank;
    }

    public String getMerDistrictCode() {
        return merDistrictCode;
    }

    public void setMerDistrictCode(String merDistrictCode) {
        this.merDistrictCode = merDistrictCode;
    }

    public String getMerCityCode() {
        return merCityCode;
    }

    public void setMerCityCode(String merCityCode) {
        this.merCityCode = merCityCode;
    }

    public String getMerProvinceCode() {
        return merProvinceCode;
    }

    public void setMerProvinceCode(String merProvinceCode) {
        this.merProvinceCode = merProvinceCode;
    }

    public String getMerLevel1NoCode() {
        return merLevel1NoCode;
    }

    public void setMerLevel1NoCode(String merLevel1NoCode) {
        this.merLevel1NoCode = merLevel1NoCode;
    }

    public String getMerLevel2NoCode() {
        return merLevel2NoCode;
    }

    public void setMerLevel2NoCode(String merLevel2NoCode) {
        this.merLevel2NoCode = merLevel2NoCode;
    }

    public String getBankCityCode() {
        return bankCityCode;
    }

    public void setBankCityCode(String bankCityCode) {
        this.bankCityCode = bankCityCode;
    }

    public String getBankProvinceCode() {
        return bankProvinceCode;
    }

    public void setBankProvinceCode(String bankProvinceCode) {
        this.bankProvinceCode = bankProvinceCode;
    }

    public String getParamerchanno() {
        return paramerchanno;
    }

    public void setParamerchanno(String paramerchanno) {
        this.paramerchanno = paramerchanno;
    }

    public String getMerContactEmail() {
        return merContactEmail;
    }

    public void setMerContactEmail(String merContactEmail) {
        this.merContactEmail = merContactEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerFullName() {
        return merFullName;
    }

    public void setMerFullName(String merFullName) {
        this.merFullName = merFullName;
    }

    public String getMerShortName() {
        return merShortName;
    }

    public void setMerShortName(String merShortName) {
        this.merShortName = merShortName;
    }

    public String getMerCertType() {
        return merCertType;
    }

    public void setMerCertType(String merCertType) {
        this.merCertType = merCertType;
    }

    public String getMerCertNo() {
        return merCertNo;
    }

    public void setMerCertNo(String merCertNo) {
        this.merCertNo = merCertNo;
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

    public String getMerContactName() {
        return merContactName;
    }

    public void setMerContactName(String merContactName) {
        this.merContactName = merContactName;
    }

    public String getTaxRegistCert() {
        return taxRegistCert;
    }

    public void setTaxRegistCert(String taxRegistCert) {
        this.taxRegistCert = taxRegistCert;
    }

    public String getAccountLicense() {
        return accountLicense;
    }

    public void setAccountLicense(String accountLicense) {
        this.accountLicense = accountLicense;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getIsOrgCodeLong() {
        return isOrgCodeLong;
    }

    public void setIsOrgCodeLong(String isOrgCodeLong) {
        this.isOrgCodeLong = isOrgCodeLong;
    }

    public String getOrgCodeExpiry() {
        return orgCodeExpiry;
    }

    public void setOrgCodeExpiry(String orgCodeExpiry) {
        this.orgCodeExpiry = orgCodeExpiry;
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

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
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

    public String getBusinessFunction() {
        return businessFunction;
    }

    public void setBusinessFunction(String businessFunction) {
        this.businessFunction = businessFunction;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getMerAuthorizeType() {
        return merAuthorizeType;
    }

    public void setMerAuthorizeType(String merAuthorizeType) {
        this.merAuthorizeType = merAuthorizeType;
    }

    public String getMerType() {
        return merType;
    }

    public void setMerType(String merType) {
        this.merType = merType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getParentMerchantNo() {
        return parentMerchantNo;
    }

    public void setParentMerchantNo(String parentMerchantNo) {
        this.parentMerchantNo = parentMerchantNo;
    }

    public String getMerContactPhone() {
        return merContactPhone;
    }

    public void setMerContactPhone(String merContactPhone) {
        this.merContactPhone = merContactPhone;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getAgreementContent() {
        return agreementContent;
    }

    public void setAgreementContent(String agreementContent) {
        this.agreementContent = agreementContent;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(String commissionRate) {
        this.commissionRate = commissionRate;
    }

    public String getMerNetInStatus() {
        return merNetInStatus;
    }

    public void setMerNetInStatus(String merNetInStatus) {
        this.merNetInStatus = merNetInStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "MerchantsInfo{" +
                "id=" + id +
                ", merFullName='" + merFullName + '\'' +
                ", merShortName='" + merShortName + '\'' +
                ", merCertType='" + merCertType + '\'' +
                ", merCertNo='" + merCertNo + '\'' +
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
                ", merContactName='" + merContactName + '\'' +
                ", taxRegistCert='" + taxRegistCert + '\'' +
                ", accountLicense='" + accountLicense + '\'' +
                ", orgCode='" + orgCode + '\'' +
                ", isOrgCodeLong='" + isOrgCodeLong + '\'' +
                ", orgCodeExpiry='" + orgCodeExpiry + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", headBankCode='" + headBankCode + '\'' +
                ", bankProvince='" + bankProvince + '\'' +
                ", bankCity='" + bankCity + '\'' +
                ", bankCode='" + bankCode + '\'' +
                ", productInfo='" + productInfo + '\'' +
                ", fileInfo='" + fileInfo + '\'' +
                ", businessFunction='" + businessFunction + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", merAuthorizeType='" + merAuthorizeType + '\'' +
                ", merType='" + merType + '\'' +
                ", createTime='" + createTime + '\'' +
                ", requestNo='" + requestNo + '\'' +
                ", parentMerchantNo='" + parentMerchantNo + '\'' +
                ", merContactPhone='" + merContactPhone + '\'' +
                ", merchantNo='" + merchantNo + '\'' +
                ", externalId='" + externalId + '\'' +
                ", agreementContent='" + agreementContent + '\'' +
                ", merContactEmail='" + merContactEmail + '\'' +
                ", returnCode='" + returnCode + '\'' +
                ", returnMsg='" + returnMsg + '\'' +
                ", commissionRate='" + commissionRate + '\'' +
                ", merNetInStatus='" + merNetInStatus + '\'' +
                ", remark='" + remark + '\'' +
                ", paramerchanno='" + paramerchanno + '\'' +
                ", bankCityCode='" + bankCityCode + '\'' +
                ", bankProvinceCode='" + bankProvinceCode + '\'' +
                ", merDistrictCode='" + merDistrictCode + '\'' +
                ", merCityCode='" + merCityCode + '\'' +
                ", merProvinceCode='" + merProvinceCode + '\'' +
                ", merLevel1NoCode='" + merLevel1NoCode + '\'' +
                ", merLevel2NoCode='" + merLevel2NoCode + '\'' +
                ", headBank='" + headBank + '\'' +
                '}';
    }
}
