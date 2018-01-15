package com.dadao.order.service;

import com.dadao.order.entity.ConsumptionRecording;
import com.dadao.order.entity.OrderPO;
import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;

import java.util.List;

/**
 * Created by NFY on 2017-08-01.
 */
public interface IOrderService {
    QueryResult findByPage(OrderPO orderPO,String token);
    ConsumptionRecording findConsumptionRecordingInfo(ConsumptionRecording consumptionRecording,String token);

    /**
     * 查询商户交易
     * @param orderPO
     * @return
     */
    List<OrderPO> findShopDeal(OrderPO orderPO);
}
