package com.dadao.business.service;

import com.dadao.business.entity.Cmerchant;
import com.dadao.utils.BasePage;

/**
 * created by GUOYU 2018/01/09
 */
public interface CmerchantService {
    /**
     * 查询所有接口
     * @param merchant
     * @return
     */
    BasePage findByAll(Cmerchant merchant);
}
