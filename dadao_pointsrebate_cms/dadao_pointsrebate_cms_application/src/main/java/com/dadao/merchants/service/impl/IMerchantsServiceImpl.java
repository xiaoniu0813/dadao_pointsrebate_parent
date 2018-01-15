package com.dadao.merchants.service.impl;

import com.dadao.merchants.activity.MerchantsActivity;
import com.dadao.merchants.entity.MerchantsSort;
import com.dadao.merchants.service.IMerchantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商户分类
 *
 * @auther NFY niufuyang
 * @create 2017-11-13
 */
@Service
public class IMerchantsServiceImpl implements IMerchantsService {

    @Autowired
    private MerchantsActivity merchantsActivity;

    @Override
    public List findSort(MerchantsSort merchantsSort) {
        return merchantsActivity.findSort(merchantsSort);
    }
}
