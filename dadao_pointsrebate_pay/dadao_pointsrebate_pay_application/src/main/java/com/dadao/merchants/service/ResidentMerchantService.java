package com.dadao.merchants.service;

import com.dadao.merchants.entity.MerchantsInfo;
import com.dadao.utils.Result;

/**
 * Created by YunQiang on 2017/11/9
 * @author YunQiang
 */
public interface ResidentMerchantService {

    /**
     * 个人注册
     * @param merchantsInfo 商户注册信息
     * @return
     */
    Result personRegInfoAdd(MerchantsInfo merchantsInfo);

    /**
     * 个体商户注册
     * @param merchantsInfo
     * @return
     */
    Result individualReginfoAdd(MerchantsInfo merchantsInfo);

    /**
     * 企业注册
     * @param merchantsInfo
     * @return
     */
    Result enterpriseReginfoAdd(MerchantsInfo merchantsInfo);

    /**
     * 注册信息分页查询
     * @param merchantsInfo 继承了page的实体类
     * @param pageNum 当前页
     * @return
     */
    Result findByPage(MerchantsInfo merchantsInfo, Long pageNum);

    /**
     * 短信验证码重发
     * @param merchantNo
     * @param phone
     * @return
     */
    Result sendauthorizenum(String merchantNo,String phone);

    /**
     *修改信息
     * @param merchantsInfo
     * @return
     */
    Result update(MerchantsInfo merchantsInfo);

}
