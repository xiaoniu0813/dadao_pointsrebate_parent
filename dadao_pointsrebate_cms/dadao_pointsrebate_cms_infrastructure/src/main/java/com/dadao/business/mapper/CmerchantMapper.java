package com.dadao.business.mapper;

import com.dadao.business.entity.Cmerchant;

import java.util.List;

/**
 * created by GUOYU 2017/01/09
 */
public interface CmerchantMapper {
    /**
     * 查询已注册但未入驻的商户集合
     * @return
     */
    List findByAll(Cmerchant merchant);

    /**
     * 查找出数据总数
     * @param merchant
     * @return
     */
    Integer findTotal(Cmerchant merchant);

}
