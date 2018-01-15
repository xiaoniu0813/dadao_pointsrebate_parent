package com.dadao.refunds.controller;

import com.dadao.pub.utils.DADAO;
import com.dadao.refunds.service.IRefundsApplicationService;
import com.dadao.shop.entity.ShopPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by YunQiang on 2017/8/9
 */
@RestController
@RequestMapping("/refunds")
public class RefundsApplicationController {

    @Autowired
    private IRefundsApplicationService iRefundsApplicationService;

    @PostMapping(value = "/application")
    public Object findRefundsByPage(@RequestParam(value = "id", required = true) Long orderId,@RequestParam(value = "token", required = true) String token,@RequestParam(value = "reason", required = true) String reason, HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request,response,iRefundsApplicationService.saveRefunds(orderId, token, reason));
    }

}
