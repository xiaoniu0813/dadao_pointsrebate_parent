package com.dadao.order.controller;

import com.dadao.order.entity.OrderPO;
import com.dadao.order.service.IOrderService;
import com.dadao.util.DADAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by YunQiang on 2017/8/8
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    @Qualifier("businessOrderService")
    private IOrderService iOrderService;

    @PostMapping(value = "/refunds")
    public Object listShop(OrderPO orderPO, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, HttpServletRequest request, HttpServletResponse response) {

        return DADAO.encryption(request, response, iOrderService.findRefundOrers(orderPO, pageNum));
    }

}
