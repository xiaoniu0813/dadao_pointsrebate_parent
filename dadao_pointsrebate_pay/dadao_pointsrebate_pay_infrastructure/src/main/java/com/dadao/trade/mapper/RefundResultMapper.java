package com.dadao.trade.mapper;

import com.dadao.order.entity.OrderPO;
import com.dadao.trade.mapper.entity.RefundResult;

/**
 * Created by YunQiang on 2017/11/24
 * @author YunQiang
 */
public interface RefundResultMapper {

    /**
     * 保存退款回执信息
     * @param orderPO 订单表对应实体类
     * @return
     */
    int insertRefundResult(OrderPO orderPO);

    /**
     * 通过orderId拿到订单
     * @param orderId 订单的ID
     * @return
     */
    OrderPO selectByOrderId(String orderId);

}
