package com.dadao.order.service.impl;

import com.dadao.activities.order.activity.OrderActivity;
import com.dadao.common.service.impl.BaseService;
import com.dadao.order.entity.ConsumptionRecording;
import com.dadao.order.entity.OrderPO;
import com.dadao.order.service.IOrderService;
import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 订单业务逻辑实现类
 *
 * @auther NFY niufuyang
 * @create 2017-08-01
 */
@Service
public class OrderServiceImpl extends BaseService implements IOrderService {
    @Autowired
    private OrderActivity orderActivity;

    public QueryResult findByPage(OrderPO orderPO,String token) {
        return orderActivity.findByPage(orderPO,token);
    }

    public ConsumptionRecording findConsumptionRecordingInfo(ConsumptionRecording consumptionRecording,String token) {
        return orderActivity.findConsumptionRecordingInfo(consumptionRecording,token);
    }

    @Override
    public List<OrderPO> findShopDeal(OrderPO orderPO) {
        return orderActivity.findShopDeal(orderPO);
    }

}
