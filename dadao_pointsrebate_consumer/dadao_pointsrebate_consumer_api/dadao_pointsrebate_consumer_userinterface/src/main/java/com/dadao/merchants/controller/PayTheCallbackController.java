package com.dadao.merchants.controller;

import com.dadao.pay.entity.PaymentDetails;
import com.dadao.merchants.service.IPayService;
import com.dadao.pub.utils.DADAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auther NFY niufuyang
 * @create 2017-10-12
 */
@RestController
public class PayTheCallbackController {
    @Autowired
    private IPayService iPayService;

    @PostMapping(value = "payTheCallback")
    public Object PayTheCallback(PaymentDetails paymentDetails, HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request, response, iPayService.PayTheCallback(paymentDetails));
    }
}
