package com.dadao.cashback.activity;

import com.dadao.cashback.entity.CashbackRecordPO;
import com.dadao.cashback.mapper.CashbackRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @auther NFY niufuyang
 * @create 2017-12-21
 */
@Repository
public class CashbackRecordActivity {
    @Autowired
    private CashbackRecordMapper cashbackRecordMapper;

    public Integer updateUserCashbackRecord(CashbackRecordPO cashbackRecordPO) {
        Integer updateResult = cashbackRecordMapper.updateUserCashbackRecord(cashbackRecordPO);
        System.out.println("---->" + updateResult);
        return updateResult;
    }
}
