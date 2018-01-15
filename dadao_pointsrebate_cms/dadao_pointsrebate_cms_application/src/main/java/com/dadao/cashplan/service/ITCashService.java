package com.dadao.cashplan.service;

import com.dadao.utils.Result;

import java.util.Date;

/**
 * Created by YunQiang on 2017/9/22
 */
public interface ITCashService {

    /**
     * 创建一条用户返利计划
     * @param marketId 市场id
     * @param cashbackSpecificDate 具体发奖时间
     * @return
     */
    public Result createCashPlan(Long marketId, Date cashbackSpecificDate);

    /**
     * 创建一条商户的返利计划
     * @param cashbackSpecificDate
     * @param marketId
     * @return
     */
    public Result createBusinessCashbackPlan(Long marketId, Date cashbackSpecificDate);

}
