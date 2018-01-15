package com.dadao.netinfo.service;

import com.dadao.merchants.entity.MerchantsInfo;
import com.dadao.utils.Result;

/**
 * created by GUOYU 2017/11/20
 */
public interface NetInfoService {

    /**
     * 商户入网信息状态
     * @param merchantsInfo
     * @return
     */
    Result netStatus(MerchantsInfo merchantsInfo);
}
