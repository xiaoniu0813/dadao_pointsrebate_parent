package com.dadao.cashback.service;

import com.dadao.capitalpool.entity.CapitalpoolRecordPO;
import com.dadao.cashback.entity.CashbackDetailsPO;

import com.dadao.cashback.entity.CashbackRecordPO;
import com.dadao.utils.POPage;
import com.dadao.utils.ResultCode;


import java.util.List;


public interface CashbackDetailsService {

    POPage showCashbackDetailsByPage(CashbackDetailsPO cashbackDetailsPO);

    List exportCashbackDetails(CashbackDetailsPO cashbackDetailsPO);

    CashbackRecordPO findCashbackDetailsPOById(long id);

    List showCashbackList(long recordId);

    ResultCode deductionPlatformCost(CapitalpoolRecordPO capitalpoolRecordPO);

}
