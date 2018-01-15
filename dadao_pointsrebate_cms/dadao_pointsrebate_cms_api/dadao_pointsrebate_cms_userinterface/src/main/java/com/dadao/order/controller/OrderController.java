package com.dadao.order.controller;

import com.dadao.order.entity.OrderPO;
import com.dadao.order.service.OrderService;
import com.dadao.utils.DADAO;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    @PostMapping(value = "findByRefund")
    public Object findByRefund(OrderPO orderPO, HttpServletRequest request, HttpServletResponse response){

        List<OrderPO> list = orderService.findByRefund(orderPO);

        return DADAO.encryption(request,response,new Result(ResultCode.SYS_SUCCESS,list));
    }
    @PostMapping(value = "updateOrderStatus")
    public Object updateOrderStatus(OrderPO orderPO, HttpServletRequest request, HttpServletResponse response){
        int status = orderService.updateOrderStatus(orderPO);
        return DADAO.encryption(request,response,new Result(status == 1 ? ResultCode.SYS_SUCCESS : ResultCode.SYS_FAIL));
    }
    @PostMapping(value = "/findAllOrder")
    public Object findAllOrder(OrderPO orderPO, HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request,response,new Result(ResultCode.SYS_SUCCESS,orderService.findAllOrder(orderPO)));
    }
    @PostMapping(value = "/findByPage")
    public Object findByPage(OrderPO orderPO, HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request,response,new Result(ResultCode.SYS_SUCCESS,orderService.findByPage(orderPO)));
    }

}
