package com.dadao.refunds.controller;

import com.dadao.refunds.entity.RefundsApplicationPO;
import com.dadao.refunds.entity.RefundsRecord;
import com.dadao.refunds.service.IRefundsApplicationService;
import com.dadao.shop.entity.ShopPO;
import com.dadao.util.DADAO;
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

    @PostMapping(value = "/list")
    public Object findRefundsByPage(ShopPO shopPO, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request,response,iRefundsApplicationService.findRefundsByPage(shopPO,pageNum));
    }

    @PostMapping(value = "/deal")
    public Object updateRefunds(RefundsApplicationPO refundsApplicationPO, HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request,response,iRefundsApplicationService.updateRefunds(refundsApplicationPO));
    }


}
