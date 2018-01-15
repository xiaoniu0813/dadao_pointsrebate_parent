package com.dadao.merchant.service.impl;

import com.dadao.merchant.activity.MerchantRegisterActivity;
import com.dadao.merchant.entity.ReturnSweepCode;
import com.dadao.merchant.service.IMerchantRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther NFY niufuyang
 * @create 2017-9-29
 */
@Service
public class IMerchantRegisterServiceImpl implements IMerchantRegisterService {
    @Autowired
    private MerchantRegisterActivity findBySubMerchantNo;

    public ReturnSweepCode findBySubMerchantNo(String subMerchantNo){
        return findBySubMerchantNo.findBySubMerchantNo(subMerchantNo);
    }
}
