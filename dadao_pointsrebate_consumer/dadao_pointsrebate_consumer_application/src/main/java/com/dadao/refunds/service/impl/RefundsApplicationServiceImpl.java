package com.dadao.refunds.service.impl;

import com.dadao.activities.refunds.activity.RefundsApplicationActivity;
import com.dadao.refunds.service.IRefundsApplicationService;
import com.dadao.utils.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by YunQiang on 2017/8/9
 */
@Service
public class RefundsApplicationServiceImpl implements IRefundsApplicationService{

    @Resource
    private RefundsApplicationActivity refundsApplicationActivity;


    @Override
    public Result saveRefunds(Long orderId, String token, String reason) {
        return refundsApplicationActivity.saveRefunds(orderId, token, reason);
    }

}
