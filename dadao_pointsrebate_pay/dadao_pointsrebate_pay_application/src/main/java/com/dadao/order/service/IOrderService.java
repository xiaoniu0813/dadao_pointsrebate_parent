package com.dadao.order.service;

import com.dadao.merchants.entity.OrderQuery;
import com.dadao.order.entity.OrderPO;
import com.dadao.shop.entity.ShopGoodsPO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by NFY on 2017-11-15.
 */
public interface IOrderService {
    Object createOrder(Integer quantity,OrderPO orderPO, Integer payMode,Long goodsId, String token, HttpServletRequest request, HttpServletResponse response);
    
    Object generateOrder(OrderPO orderPO,String token, HttpServletRequest request, HttpServletResponse response);

    /**
     *
     * @param orderQuery
     * @return
     */
    OrderQuery multiOrderQuery(OrderQuery orderQuery);
}
