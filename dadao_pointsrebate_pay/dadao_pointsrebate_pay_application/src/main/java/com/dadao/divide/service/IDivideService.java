package com.dadao.divide.service;

import com.dadao.order.entity.OrderVO;

import java.math.BigDecimal;

/**
 * Created by NFY on 2017-11-23.
 */
public interface IDivideService {
    String divided(OrderVO orderVO,BigDecimal CommissionMoney) throws Exception;
}
