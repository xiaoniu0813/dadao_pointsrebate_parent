package com.dadao.merchants.service.impl;

import com.dadao.activities.pay.activity.PayTheCallbackActivity;
import com.dadao.merchants.service.IPayService;
import com.dadao.pay.entity.PaymentDetails;
import com.dadao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther NFY niufuyang
 * @create 2017-10-12
 */
@Service
public class IPayServiceImpl implements IPayService {
    @Autowired
    private PayTheCallbackActivity payTheCallbackActivity;

    public Result PayTheCallback(PaymentDetails paymentDetails){
        return payTheCallbackActivity.PayTheCallback(paymentDetails);
    }
}
