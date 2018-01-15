package com.dadao.refunds.service.impl;

import com.dadao.refunds.activity.RefundsApplicationActivity;
import com.dadao.refunds.entity.RefundsApplicationPO;
import com.dadao.refunds.mapper.entity.RefundsMessage;
import com.dadao.refunds.service.RefundsApplicationService;
import com.dadao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefundsApplicationServiceImpl implements RefundsApplicationService {

    @Autowired
    private RefundsApplicationActivity refundsApplicationActivity;
    /**
     * 根据orderId查询出退款信息
     * @param orderId
     * @return
     */
    @Override
    public RefundsApplicationPO findByRefundsInfo(long orderId) {
        return refundsApplicationActivity.findByRefundsInfo(orderId);
    }

    @Override
    public Result findByConditions(RefundsMessage refundsMessage, Long pageNum, int pageSize) {
        return refundsApplicationActivity.findByConditions(refundsMessage, pageNum, pageSize);
    }
}
