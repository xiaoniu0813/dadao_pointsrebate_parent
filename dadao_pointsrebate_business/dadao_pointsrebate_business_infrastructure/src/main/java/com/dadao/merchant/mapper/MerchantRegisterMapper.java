package com.dadao.merchant.mapper;

import com.dadao.merchant.entity.ReturnSweepCode;
import com.dadao.pub.mapper.BaseMapper;

/**
 * Created by NFY on 2017-9-29.
 */
public interface MerchantRegisterMapper extends BaseMapper {
    ReturnSweepCode findBySubMerchantNo(String subMerchantNo);
}
