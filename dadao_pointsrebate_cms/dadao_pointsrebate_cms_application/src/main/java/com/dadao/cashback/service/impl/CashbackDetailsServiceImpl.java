package com.dadao.cashback.service.impl;

import com.dadao.capitalpool.entity.CapitalpoolRecordPO;
import com.dadao.cashback.activity.CashbackDetailsActivity;
import com.dadao.cashback.entity.CashbackDetailsPO;

import com.dadao.cashback.entity.CashbackRecordPO;
import com.dadao.cashback.service.CashbackDetailsService;
import com.dadao.utils.POPage;

import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashbackDetailsServiceImpl implements CashbackDetailsService {
    @Autowired
    private CashbackDetailsActivity cashbackDetailsActivity;

    public POPage showCashbackDetailsByPage(CashbackDetailsPO cashbackDetailsPO){
        return cashbackDetailsActivity.showCashbackDetailsByPage(cashbackDetailsPO);
    }

    public List exportCashbackDetails(CashbackDetailsPO cashbackDetailsPO) {
        return cashbackDetailsActivity.exportCashbackDetails(cashbackDetailsPO);
    }

    public CashbackRecordPO findCashbackDetailsPOById(long id) {
        return cashbackDetailsActivity.findCashbackDetailsPOById(id);
    }
    public List showCashbackList(long recordId){
        return cashbackDetailsActivity.showCashbackList(recordId);
    }

    public ResultCode deductionPlatformCost(CapitalpoolRecordPO capitalpoolRecordPO) {
        return cashbackDetailsActivity.deductionPlatformCost(capitalpoolRecordPO);
    }
}
