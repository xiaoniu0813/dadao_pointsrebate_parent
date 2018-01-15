package com.dadao.order.service.impl;

import com.dadao.order.activity.OrderActivity;
import com.dadao.order.entity.OrderPO;
import com.dadao.order.service.IOrderService;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by YunQiang on 2017/8/8
 */
@Service("businessOrderService")
public class OrderServiceImpl implements IOrderService{

    @Autowired
    private OrderActivity orderActivity;

    public Result findRefundOrers(OrderPO orderPO, Integer pageNum) {
        if(orderPO.getShopId() == null){
            return new Result(ResultCode.ENTITY_ID_NULL,false);
        }
        return new Result(ResultCode.SYS_SUCCESS, orderActivity.findRefundOrers(orderPO, pageNum));
    }
}
