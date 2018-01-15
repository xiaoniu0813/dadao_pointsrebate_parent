package com.dadao.cashplan.service.impl;

import com.dadao.cashplan.activity.TCashActivity;
import com.dadao.cashplan.service.ITCashService;
import com.dadao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by YunQiang on 2017/9/22
 */
@Service
public class ITCashServiceImpl implements ITCashService {

    @Autowired
    private TCashActivity tCashActivity;

    public Result createCashPlan(Long marketId, Date cashbackSpecificDate) {
        return tCashActivity.saveCashPlan(marketId,cashbackSpecificDate);
    }

    public Result createBusinessCashbackPlan(Long marketId, Date cashbackSpecificDate) {
        return tCashActivity.createBusinessCashbackPlan(marketId, cashbackSpecificDate);
    }

}
