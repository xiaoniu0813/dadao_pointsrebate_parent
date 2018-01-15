package com.dadao.merchant.activity;

import com.dadao.merchant.entity.ReturnSweepCode;
import com.dadao.merchant.mapper.MerchantRegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @auther NFY niufuyang
 * @create 2017-9-29
 */
@Repository
public class MerchantRegisterActivity {
    @Autowired
    private MerchantRegisterMapper merchantRegisterMapper;

    public ReturnSweepCode findBySubMerchantNo(String subMerchantNo){
        return merchantRegisterMapper.findBySubMerchantNo(subMerchantNo);
    }
}
