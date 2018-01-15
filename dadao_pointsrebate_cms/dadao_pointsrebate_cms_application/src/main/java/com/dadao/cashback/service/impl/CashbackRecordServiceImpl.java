package com.dadao.cashback.service.impl;

import com.dadao.cashback.activity.CashbackRecordActivity;
import com.dadao.cashback.entity.CashbackRecordPO;
import com.dadao.cashback.service.CashbackRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther NFY niufuyang
 * @create 2017-12-21
 */
@Service
public class CashbackRecordServiceImpl implements CashbackRecordService {

   @Autowired
   private CashbackRecordActivity cashbackRecordActivity;

    @Override
    public Integer updateUserCashbackRecord(CashbackRecordPO cashbackRecordPO) {
        return cashbackRecordActivity.updateUserCashbackRecord(cashbackRecordPO);
    }
}
