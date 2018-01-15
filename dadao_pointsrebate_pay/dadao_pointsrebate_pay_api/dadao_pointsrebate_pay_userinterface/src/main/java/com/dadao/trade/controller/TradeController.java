package com.dadao.trade.controller;

import com.dadao.trade.mapper.entity.RefundResult;
import com.dadao.trade.service.TradeService;
import com.dadao.trade.service.entity.RefundPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by YunQiang on 2017/11/21
 * @author YunQiang
 * @description 交易
 */
@RestController
@RequestMapping("/trade")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @PostMapping("/refund")
    public Object refund(@RequestParam(required = true) int status, String platformDescription, HttpServletRequest request, HttpServletResponse response,@RequestParam(required = true) Long ...id){
        return tradeService.refund(status, platformDescription, id);
    }

    @PostMapping("/refund/query")
    public Object refundQuery(@RequestParam(required = true) Long id, HttpServletRequest request, HttpServletResponse response){
        return tradeService.refundQuery(id);
    }

    @PostMapping("/refund/result")
    public Object refundResult(String response, HttpServletRequest request, HttpServletResponse httpServletResponse){
        return tradeService.refundResult(response);
    }



}
