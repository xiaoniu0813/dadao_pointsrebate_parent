package com.dadao.order.service;

import com.dadao.order.entity.OrderPO;
import com.dadao.utils.Result;

/**
 * Created by YunQiang on 2017/8/8
 */
public interface IOrderService {

    public Result findRefundOrers(OrderPO orderPO, Integer pageNum);

}
