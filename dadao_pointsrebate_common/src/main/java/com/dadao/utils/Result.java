package com.dadao.utils;

/**
 * @Author: yangrui
 * @Description: 数据封装
 * @Date: 下午2:55 2017/7/16
 */
public class Result {

    public Result(String code) {
        this.code = code;
    }

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode().toString();
        this.descirption = resultCode.getDescription();
    }

    public Result(ResultCode resultCode, Object obj) {
        this.code = resultCode.getCode().toString();
        this.descirption = resultCode.getDescription();
        this.object = obj;
    }

    public Result(String code, Object object) {
        this.code = code;
        this.object = object;
    }

    public Result(String code, String descirption, Object object) {
        this.code = code;
        this.descirption = descirption;
        this.object = object;
    }

    private String code;
    private String descirption;
    private Object object;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescirption() {
        return descirption == null ? "" : descirption;
    }

    public void setDescirption(String descirption) {
        this.descirption = descirption;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
