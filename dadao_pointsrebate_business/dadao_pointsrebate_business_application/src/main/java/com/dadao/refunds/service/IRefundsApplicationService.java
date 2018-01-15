package com.dadao.refunds.service;

import com.dadao.refunds.entity.RefundsApplicationPO;
import com.dadao.shop.entity.ShopPO;
import com.dadao.utils.Result;

/**
 * Created by YunQiang on 2017/8/9
 */
public interface IRefundsApplicationService {

    public Result findRefundsByPage(ShopPO shopPO, Integer pageNum);

    public Result updateRefunds(RefundsApplicationPO input);
}
