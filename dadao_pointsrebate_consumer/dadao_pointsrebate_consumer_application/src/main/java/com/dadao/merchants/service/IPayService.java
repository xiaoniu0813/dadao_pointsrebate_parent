package com.dadao.merchants.service;

import com.dadao.pay.entity.PaymentDetails;
import com.dadao.utils.Result;

/**
 * Created by NFY on 2017-10-12.
 */
public interface IPayService {
    Result PayTheCallback(PaymentDetails paymentDetails);
}
