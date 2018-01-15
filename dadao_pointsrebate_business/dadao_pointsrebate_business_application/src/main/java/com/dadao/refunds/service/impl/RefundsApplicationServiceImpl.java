package com.dadao.refunds.service.impl;

import com.dadao.refunds.activity.RefundsApplicationActivity;
import com.dadao.refunds.entity.RefundsApplicationPO;
import com.dadao.refunds.service.IRefundsApplicationService;
import com.dadao.shop.entity.ShopPO;
import com.dadao.utils.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by YunQiang on 2017/8/9
 */
@Service
public class RefundsApplicationServiceImpl implements IRefundsApplicationService {

    @Resource
    private RefundsApplicationActivity refundsApplicationActivity;

    public Result findRefundsByPage(ShopPO shopPO, Integer pageNum) {
        return refundsApplicationActivity.findRefundsByPage(shopPO, pageNum);
    }

    public Result updateRefunds(RefundsApplicationPO input) {
        return refundsApplicationActivity.updateRefundsById(input);
    }
}
