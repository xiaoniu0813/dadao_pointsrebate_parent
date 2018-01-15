package com.dadao.merchant.service;

import com.dadao.merchant.entity.ReturnSweepCode;

/**
 * Created by NFY on 2017-9-29.
 */
public interface IMerchantRegisterService {
    ReturnSweepCode findBySubMerchantNo(String subMerchantNo);
}
