package com.dadao.order.controller;

import com.dadao.divide.service.IDivideService;
import com.dadao.merchants.entity.Order;
import com.dadao.merchants.entity.OrderQuery;
import com.dadao.order.entity.OrderPO;
import com.dadao.order.service.IOrderService;
import com.dadao.shop.entity.ShopGoodsPO;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 对接易宝支付订单
 *
 * @auther NFY niufuyang
 * @create 2017-11-16
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private IOrderService iOrderService;
    @Autowired
    private IDivideService iDivideService;

    @PostMapping(value = "/getPayUrl")
    public Object getPayUrl(@RequestParam(value = "quantity", required = false, defaultValue = "1") Integer quantity, Integer payMode,Long goodsId, HttpServletRequest request, HttpServletResponse response, OrderPO orderPO, ShopGoodsPO shopGoodsPO, String token) {
        return iOrderService.createOrder(quantity, orderPO, payMode, goodsId, token, request, response);
    }

    @PostMapping(value = "/generateOrder")
    public Object generateOrder(HttpServletRequest request, HttpServletResponse response, OrderPO orderPO, String token) {
        return iOrderService.generateOrder(orderPO, token, request, response);
    }

    @PostMapping(value = "/multiOrder")
    public Object multiOrder(HttpServletRequest request, HttpServletResponse response, OrderQuery orderQuery) {
        return new Result(ResultCode.SYS_SUCCESS, iOrderService.multiOrderQuery(orderQuery));

    }

    @PostMapping(value = "onlinePay")
    public Object onlinePay(Order order, ShopGoodsPO shopGoodsPO) {
        System.out.println(order);
        System.out.println(shopGoodsPO);
        return null;
    }
}
