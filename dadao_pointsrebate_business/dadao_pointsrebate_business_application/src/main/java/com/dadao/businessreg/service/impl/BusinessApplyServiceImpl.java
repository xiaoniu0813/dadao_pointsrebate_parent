package com.dadao.businessreg.service.impl;


import com.dadao.businessreg.activity.BusinessApplyActivity;
import com.dadao.businessreg.entity.BusinessApply;
import com.dadao.businessreg.service.BusinessApplyService;
import com.dadao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author GUOYU 2017/12/13
 */
@Service
public class BusinessApplyServiceImpl implements BusinessApplyService {

    @Autowired
    private BusinessApplyActivity businessApplyActivity;
    @Override
    public Result reg(BusinessApply businessApply) {
        return businessApplyActivity.reg(businessApply);
    }

    @Override
    public BusinessApply findByUserId(BusinessApply businessApply) {
        return businessApplyActivity.findByUserId(businessApply);
    }

    @Override
    public Result update(BusinessApply businessApply) {
        return businessApplyActivity.update(businessApply);
    }
}
