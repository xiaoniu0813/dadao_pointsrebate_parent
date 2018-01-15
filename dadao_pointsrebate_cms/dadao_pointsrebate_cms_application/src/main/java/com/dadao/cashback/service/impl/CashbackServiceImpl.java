package com.dadao.cashback.service.impl;

import com.dadao.cashback.activity.CashbackActivity;
import com.dadao.cashback.activity.CashbackDetailsActivity;
import com.dadao.cashback.entity.CashbackRecordPO;
import com.dadao.cashback.mapper.CashbackRecordMapper;
import com.dadao.cashback.entity.CashbackRecordPO;
import com.dadao.cashback.service.CashbackService;
import com.dadao.user.activity.UserIntegralRecordingActivity;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 定时器控制返现
 *
 * @auther NFY niufuyang
 * @create 2017-9-8
 */
@Service
public class CashbackServiceImpl implements CashbackService {
    @Autowired
    private CashbackDetailsActivity cashbackDetailsActivity;
    @Autowired
    private CashbackActivity cashbackActivity;

    @Autowired
    private CashbackRecordMapper cashbackRecordMapper;

    @Override
    public void timingScanningCashback() throws Exception {
        cashbackDetailsActivity.timingScanningCashback();
    }

    @Override
    public QueryResult findCashbackPlanList(CashbackRecordPO cashbackRecordPO, Integer pageNum) {
        return cashbackActivity.findCashbackPlanList(cashbackRecordPO,pageNum);
    }

    @Override
    public Result cashToBusinesses(long id, Long recordId) {
        //找到商户返利记录
        CashbackRecordPO cashbackRecordPO = (CashbackRecordPO) cashbackRecordMapper.findById(recordId);
        if(cashbackRecordPO != null){
            cashbackActivity.cashToBusiness(id, cashbackRecordPO.getRecordId(), new BigDecimal(cashbackRecordPO.getCashbackMultiple()));
        }
        return new Result(ResultCode.SYS_SUCCESS, "返利成功");
    }
}
