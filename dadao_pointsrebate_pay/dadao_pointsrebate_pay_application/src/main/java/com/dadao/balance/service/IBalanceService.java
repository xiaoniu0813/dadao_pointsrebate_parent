package com.dadao.balance.service;

import com.dadao.utils.Result;

import java.math.BigDecimal;

/**
 * Created by NFY on 2017-11-13.
 */
public interface IBalanceService {
    BigDecimal findBalance(String token);

    /**
     * 根据商户token查询余额
     * @param token 商户token
     * @return
     */
    Result queryBusinessBalance(String token);

}
