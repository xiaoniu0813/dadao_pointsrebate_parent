package com.dadao.merchants.activity;

import com.dadao.merchants.entity.MerchantsSort;
import com.dadao.merchantssort.mapper.MerchantsSortMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商户分类
 *
 * @auther NFY niufuyang
 * @create 2017-11-13
 */
@Repository
public class MerchantsActivity {

    @Autowired
    private MerchantsSortMapper merchantsSortMapper;

    public List findSort(MerchantsSort merchantsSort){
        return merchantsSortMapper.findSort(merchantsSort);
    }
}
