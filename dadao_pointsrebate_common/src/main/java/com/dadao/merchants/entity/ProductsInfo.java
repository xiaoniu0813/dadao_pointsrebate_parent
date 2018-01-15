package com.dadao.merchants.entity;

/**
 * Created by YunQiang on 2017/11/14
 *
 * @author YunQiang
 */
public class ProductsInfo {

    /**
     * 商户支付场景
     * ----------------------
     * ①EWB网站：WEB_ACCESS
     * ②H5页面：H5_ACCESS
     * ③APP：APP_ACCESS
     * ④公众号支付：OFFICIAL_ACCOUNT_ACCESS
     * ⑤生活号支付：FACE_TO_FACE_ACCESS
     * ⑥面对面支付：ZF_SHH_ZFB
     */
    private String[] payScenarioMap;

    /**
     * 商户接入网址(用于WEB网站)
     */
    private String webUrl_web;

    /**
     * icp备案号
     */
    private String icp;

    /**
     * 商户接入网址
     */
    private String webUrl_h5;

    /**
     * APP名称
     */
    private String appName;

    /**
     * APP下载地址
     */
    private String appDownloadUrl;


    /**
     * 商户支付产品
     * ----------------------
     * ①一键支付：ONEKEY
     * ②网银支付：NETBANK
     * ③用户扫码：SCANPAY
     * ④公众号支付：WECHATOPEN
     * ⑤生活号支付：ZFBSHH
     * ⑥商家扫码：MSCANPAY
     * ⑦钱包H5支付：EWALLETH5
     */
    private String[] payProductMap;

    /**
     * 借记卡交易手续费(百分比)
     */
    private String ONE_KEY_PAY_DEBIT;

    /**
     *贷记卡交易手续费(百分比)
     */
    private String ONE_KEY_PAY_CREDIT;

    /**
     *网银B2C交易手续费(百分比)
     */
    private String B2C_PAY;

    /**
     *网银B2B交易手续费（单笔）
     */
    private String B2B_PAY;

    /**
     *微信交易手续费(百分比)
     */
    private String WECHAT_ATIVE_SCAN;

    /**
     *支付宝交易手续费（百分比）
     */
    private String ALIPAY;

    /**
     *京东交易手续费（百分比）
     */
    private String JD_ATIVE_SCAN;

    /**
     * 银联交易手续费（百分比）
     */
    private String UPOP_ATIVE_SCAN;

    /**
     * 商户公众微信号
     */
    private String weChatId;

    /**
     *公众服务号APPID
     */
    private String officialAccAppId;

    /**
     *推荐关注服务号APPID
     */
    private String recommendOfficialAccAppId;

    /**
     * 支付授权目录
     */
    private String officialAccAuthorizeDirectory;

    /**
     *公众号支付交易手续费（百分比）
     */
    private String OFFICIAL_ACCOUNT_PAY;

    /**
     * 生活号支付,支付号PID
     */
    private String aliPayPID;

    /**
     *生活号支付交易手续费(百分比)
     */
    private String ZFB_SHH;

    /**
     *微信交易手续费（百分比）
     */
    private String WECHAT_SCAN;

    /**
     *支付宝交易手续费（百分比）
     */
    private String ALIPAY_SCAN;

    /**
     *京东交易手续费（百分比）
     */
    private String JD_PASSIVE_SCAN;

    /**
     *银联交易手续费（百分比）
     */
    private String UPOP_PASSIVE_SCAN;

    /**
     *微信H5交易手续费（百分比）
     */
    private String WECHAT_H5;

    /**
     *支付宝H5交易手续费（百分比）
     */
    private String ALIPAY_H5;

    public String[] getPayScenarioMap() {
        return payScenarioMap;
    }

    public void setPayScenarioMap(String[] payScenarioMap) {
        this.payScenarioMap = payScenarioMap;
    }

    public String getWebUrl_web() {
        return webUrl_web;
    }

    public void setWebUrl_web(String webUrl_web) {
        this.webUrl_web = webUrl_web;
    }

    public String getIcp() {
        return icp;
    }

    public void setIcp(String icp) {
        this.icp = icp;
    }

    public String getWebUrl_h5() {
        return webUrl_h5;
    }

    public void setWebUrl_h5(String webUrl_h5) {
        this.webUrl_h5 = webUrl_h5;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppDownloadUrl() {
        return appDownloadUrl;
    }

    public void setAppDownloadUrl(String appDownloadUrl) {
        this.appDownloadUrl = appDownloadUrl;
    }

    public String[] getPayProductMap() {
        return payProductMap;
    }

    public void setPayProductMap(String[] payProductMap) {
        this.payProductMap = payProductMap;
    }

    public String getONE_KEY_PAY_DEBIT() {
        return ONE_KEY_PAY_DEBIT;
    }

    public void setONE_KEY_PAY_DEBIT(String ONE_KEY_PAY_DEBIT) {
        this.ONE_KEY_PAY_DEBIT = ONE_KEY_PAY_DEBIT;
    }

    public String getONE_KEY_PAY_CREDIT() {
        return ONE_KEY_PAY_CREDIT;
    }

    public void setONE_KEY_PAY_CREDIT(String ONE_KEY_PAY_CREDIT) {
        this.ONE_KEY_PAY_CREDIT = ONE_KEY_PAY_CREDIT;
    }

    public String getB2C_PAY() {
        return B2C_PAY;
    }

    public void setB2C_PAY(String b2C_PAY) {
        B2C_PAY = b2C_PAY;
    }

    public String getB2B_PAY() {
        return B2B_PAY;
    }

    public void setB2B_PAY(String b2B_PAY) {
        B2B_PAY = b2B_PAY;
    }

    public String getWECHAT_ATIVE_SCAN() {
        return WECHAT_ATIVE_SCAN;
    }

    public void setWECHAT_ATIVE_SCAN(String WECHAT_ATIVE_SCAN) {
        this.WECHAT_ATIVE_SCAN = WECHAT_ATIVE_SCAN;
    }

    public String getALIPAY() {
        return ALIPAY;
    }

    public void setALIPAY(String ALIPAY) {
        this.ALIPAY = ALIPAY;
    }

    public String getJD_ATIVE_SCAN() {
        return JD_ATIVE_SCAN;
    }

    public void setJD_ATIVE_SCAN(String JD_ATIVE_SCAN) {
        this.JD_ATIVE_SCAN = JD_ATIVE_SCAN;
    }

    public String getUPOP_ATIVE_SCAN() {
        return UPOP_ATIVE_SCAN;
    }

    public void setUPOP_ATIVE_SCAN(String UPOP_ATIVE_SCAN) {
        this.UPOP_ATIVE_SCAN = UPOP_ATIVE_SCAN;
    }

    public String getWeChatId() {
        return weChatId;
    }

    public void setWeChatId(String weChatId) {
        this.weChatId = weChatId;
    }

    public String getOfficialAccAppId() {
        return officialAccAppId;
    }

    public void setOfficialAccAppId(String officialAccAppId) {
        this.officialAccAppId = officialAccAppId;
    }

    public String getRecommendOfficialAccAppId() {
        return recommendOfficialAccAppId;
    }

    public void setRecommendOfficialAccAppId(String recommendOfficialAccAppId) {
        this.recommendOfficialAccAppId = recommendOfficialAccAppId;
    }

    public String getOfficialAccAuthorizeDirectory() {
        return officialAccAuthorizeDirectory;
    }

    public void setOfficialAccAuthorizeDirectory(String officialAccAuthorizeDirectory) {
        this.officialAccAuthorizeDirectory = officialAccAuthorizeDirectory;
    }

    public String getOFFICIAL_ACCOUNT_PAY() {
        return OFFICIAL_ACCOUNT_PAY;
    }

    public void setOFFICIAL_ACCOUNT_PAY(String OFFICIAL_ACCOUNT_PAY) {
        this.OFFICIAL_ACCOUNT_PAY = OFFICIAL_ACCOUNT_PAY;
    }

    public String getAliPayPID() {
        return aliPayPID;
    }

    public void setAliPayPID(String aliPayPID) {
        this.aliPayPID = aliPayPID;
    }

    public String getZFB_SHH() {
        return ZFB_SHH;
    }

    public void setZFB_SHH(String ZFB_SHH) {
        this.ZFB_SHH = ZFB_SHH;
    }

    public String getWECHAT_SCAN() {
        return WECHAT_SCAN;
    }

    public void setWECHAT_SCAN(String WECHAT_SCAN) {
        this.WECHAT_SCAN = WECHAT_SCAN;
    }

    public String getALIPAY_SCAN() {
        return ALIPAY_SCAN;
    }

    public void setALIPAY_SCAN(String ALIPAY_SCAN) {
        this.ALIPAY_SCAN = ALIPAY_SCAN;
    }

    public String getJD_PASSIVE_SCAN() {
        return JD_PASSIVE_SCAN;
    }

    public void setJD_PASSIVE_SCAN(String JD_PASSIVE_SCAN) {
        this.JD_PASSIVE_SCAN = JD_PASSIVE_SCAN;
    }

    public String getUPOP_PASSIVE_SCAN() {
        return UPOP_PASSIVE_SCAN;
    }

    public void setUPOP_PASSIVE_SCAN(String UPOP_PASSIVE_SCAN) {
        this.UPOP_PASSIVE_SCAN = UPOP_PASSIVE_SCAN;
    }

    public String getWECHAT_H5() {
        return WECHAT_H5;
    }

    public void setWECHAT_H5(String WECHAT_H5) {
        this.WECHAT_H5 = WECHAT_H5;
    }

    public String getALIPAY_H5() {
        return ALIPAY_H5;
    }

    public void setALIPAY_H5(String ALIPAY_H5) {
        this.ALIPAY_H5 = ALIPAY_H5;
    }
}
