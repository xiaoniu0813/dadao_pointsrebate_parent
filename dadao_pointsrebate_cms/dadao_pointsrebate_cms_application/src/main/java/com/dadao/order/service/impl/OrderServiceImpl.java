package com.dadao.order.service.impl;

import com.dadao.order.activity.OrderActivity;
import com.dadao.order.entity.OrderPO;
import com.dadao.order.service.OrderService;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderActivity orderActivity;

    public List<OrderPO> findByRefund(OrderPO orderPO) {
        return orderActivity.findByRefund(orderPO);
    }

    public int updateOrderStatus(OrderPO orderPO) {
        return orderActivity.updateOrderStatus(orderPO);
    }

    @Override
    public List<OrderPO> findAllOrder(OrderPO orderPO) {
        return orderActivity.findAllOrder(orderPO);
    }

    @Override
    public QueryResult findByPage(OrderPO orderPO) {
        return orderActivity.findByPage(orderPO);
    }


}
