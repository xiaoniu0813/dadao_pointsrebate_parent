package com.dadao.cashback.mapper;

import com.dadao.cashback.entity.CashbackRecordPO;
import com.dadao.pub.mapper.BaseMapper;

import java.util.List;

public interface CashbackRecordMapper extends BaseMapper {

    List findNotStartedCashback();

    String findLastRebateDate();

    int updateUserCashbackRecord(CashbackRecordPO cashbackRecordPO);
}
