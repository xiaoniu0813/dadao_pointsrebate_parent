package com.dadao.utils;

/**
 * Created by e on 2017-07-29.
 */
public enum ResultCode {

    //1开头为系统码
    SYS_FAIL(-1, "系统处理失败"),
    SYS_SUCCESS(1000, "系统处理成功"),

    //2开头为业务
    LOGIN_PHONE_FAIL(2001,"手机号输入错误"),
    LOGIN_ACCOUNT_FAIL(2002,"请检查用户名或密码"),
    CODE_NULL(2003, "验证码不能为空"),
    CODE_FAIL(2004,"验证码错误"),
    LOGIN_PHONE_NULL(2005, "用户未注册"),
    REGISTER_EXIST_FAIL(2006, "该手机号已被注册"),
    LOGIN_PASSWORD_NULL(2007, "密码不能为空"),
    ENTITY_ID_NULL(2008, "ID不能为空"),
    ORDER_DESCRIPTION_NULL(2009, "退款原因不能为空"),
    ORDER_REFUND_FAIL(2010, "申请退款失败,请联系店主"),
    USER_ACCOUNT_NULL(2011, "用户未登陆，或者无权限"),
    USER_TOKEN_INVALID(2012,"无效的TOKEN值"),
    USER_BALANCE_FAIL(2013,"用户余额获取失败"),
    USER_WITHDRAW_FAIL(2014,"提现金额大于可用余额"),
    WITHDRQW_MINIMAL_QUOTA(2015,"提现金额必须大于10元"),
    ENTITY_ID_FAIL(2016,"请检查id是否正确"),
    USER_PAYPASSWOED_ERROR(2017,"支付密码错误"),
    INPUT_PARAMS_FAIL(2018,"参数错误"),
    USER_LOGIN_FAIL(2019,"非法登录"),
    DEDUCTIONPLATFORMCOST_FAIL(2020,"扣除平台成本失败"),
    CASHBACK_FAIL(2021,"返现失败当前没有达到返利标准的用户"),
    CAPITALPOOL_FAIL(2022,"当前资金池金额小于返现总金额"),
    UPDATEPHONE_FAIL(2023,"手机号在当前端已存在"),
    PAYTHECALLBACK_PRM_FAIL(2024,"支付回调所需参数为空"),
    PAYTHECALLBACK_PAY_FAIL(2025,"支付状态异常或未完成支付"),
    PAYTHECALLBACK_SHOPID_FAIL(2026,"shopId丢失或无效"),
    YOP_ERROR_MSG(2027,"请求第三方易宝错误"),
    INFO_NOTEMPTY(2028,"信息不能为空");





    /**
     * 返回编码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String description;

    ResultCode(int code, String description){
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

