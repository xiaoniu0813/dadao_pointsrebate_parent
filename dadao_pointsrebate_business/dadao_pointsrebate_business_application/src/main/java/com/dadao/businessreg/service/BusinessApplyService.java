package com.dadao.businessreg.service;


import com.dadao.businessreg.entity.BusinessApply;
import com.dadao.utils.Result;

/**
 * @Author GUOYU 2017/12/13
 */
public interface BusinessApplyService {
    /**
     * 个体注册 企业注册
     * @param businessApply
     * @return
     */
    Result reg(BusinessApply businessApply);

    /**
     * 查询商户状态
     * @param businessApply
     * @return
     */
    BusinessApply findByUserId(BusinessApply businessApply);

    /**
     * 修改商户入驻信息
     * @param businessApply
     * @return
     */
    Result update(BusinessApply businessApply);

}
