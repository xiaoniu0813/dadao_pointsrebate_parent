package com.dadao.refunds.service;

import com.dadao.utils.Result;

/**
 * Created by YunQiang on 2017/8/9
 */
public interface IRefundsApplicationService {

    Result saveRefunds(Long orderId, String token, String reason);

}
