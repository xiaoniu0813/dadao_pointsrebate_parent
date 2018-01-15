package com.dadao.merchantssort.mapper;

import com.dadao.merchants.entity.MerchantsSort;
import com.dadao.pub.mapper.BaseMapper;

import java.util.List;

/**
 * 商户分类
 *
 * @auther NFY niufuyang
 * @create 2017-11-13
 */
public interface MerchantsSortMapper extends BaseMapper {
    List findSort(MerchantsSort merchantsSort);
}
