package com.dadao.business.service;

import com.dadao.businessreg.entity.BusinessApply;
import com.dadao.utils.BasePage;
import com.dadao.utils.Page;
import com.dadao.utils.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author GUOYU 2017/12/13
 */
public interface BusinessApplyService {
    /**
     * 个体 企业注册
     * @param businessApply
     * @return
     */
    Result reg(BusinessApply businessApply);

    /**
     * 查找个体 企业注册信息
     * @param businessApply
     * @return
     */
    BasePage findUserRegisterInfo(BusinessApply businessApply);

    /**
     * 查询单条数据
     * @param businessApply
     * @return
     */
    BusinessApply findSingletonData(BusinessApply businessApply);

    void findByAll(HttpServletRequest request, HttpServletResponse response, com.dadao.shop.entity.BusinessApply businessApply);

}
