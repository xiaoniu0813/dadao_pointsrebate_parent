package com.dadao.business.service.impl;

import com.dadao.business.activity.CmerchantActivity;
import com.dadao.business.entity.Cmerchant;
import com.dadao.business.service.CmerchantService;
import com.dadao.utils.BasePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created by GUOYU 2018/01/09
 */
@Service
public class CmerchantServiceImpl implements CmerchantService {
    @Autowired
    private CmerchantActivity cmerchantActivity;

    @Override
    public BasePage findByAll(Cmerchant merchant) {
        return cmerchantActivity.findByAll(merchant);
    }

}
