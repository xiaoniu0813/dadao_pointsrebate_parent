package com.dadao.order.controller;

import com.dadao.order.entity.ConsumptionRecording;
import com.dadao.order.entity.OrderPO;
import com.dadao.order.service.IOrderService;
import com.dadao.pub.utils.DADAO;
import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 订单表维护
 *
 * @auther NFY niufuyang
 * @create 2017-08-01
 */
@RestController
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @PostMapping(value = "/ConsumptionRecordingList")
    public Object findConsumptionRecording(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum, String token, OrderPO orderPO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        pageNum = pageNum <= 0 ? 1 : pageNum;
        orderPO.setBeginIndex(Long.valueOf((pageNum - 1) * orderPO.getPageSize()));
        QueryResult result = orderService.findByPage(orderPO, token);
        if (result != null)
            result.setPageNum(pageNum);
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, result));
    }

    @PostMapping(value = "/ConsumptionRecordingInfo")
    public Object findConsumptionRecordingById(String token, ConsumptionRecording consumptionRecording, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ConsumptionRecording recording = orderService.findConsumptionRecordingInfo(consumptionRecording, token);
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, recording));
    }

    @PostMapping(value = "/findShopDeal")
    public Object findShopDeal(OrderPO orderPO,HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, orderService.findShopDeal(orderPO)));
    }

}
