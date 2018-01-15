package com.dadao.order.service;

import com.dadao.order.entity.OrderPO;
import com.dadao.utils.QueryResult;

import java.util.List;

public interface OrderService {

    /**
     * 退款申请
     * @param orderPO
     * @return
     */
    List<OrderPO> findByRefund(OrderPO orderPO);

    /**
     * 更改退款状态
     * @param orderPO
     * @return
     */
    int updateOrderStatus(OrderPO orderPO);

    /**
     * 查询所有商户订单信息
     * @param orderPO
     * @return
     */
    List<OrderPO> findAllOrder(OrderPO orderPO);

    /**
     * 分页
     * @param orderPO
     * @return
     */
    QueryResult findByPage(OrderPO orderPO);


}
