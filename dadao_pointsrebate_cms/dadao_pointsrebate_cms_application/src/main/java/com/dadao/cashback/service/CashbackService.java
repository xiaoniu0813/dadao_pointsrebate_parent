package com.dadao.cashback.service;

import com.dadao.utils.Result;

import com.dadao.cashback.entity.CashbackRecordPO;
import com.dadao.utils.QueryResult;

/**
 * Created by NFY on 2017-9-8.
 */
public interface CashbackService {
    void timingScanningCashback() throws Exception;


    /**
     * 为单个商户返利
     * @param id 返利专用id
     * @param recordId 返现记录表主键
     */
    Result cashToBusinesses(long id, Long recordId);


    QueryResult findCashbackPlanList(CashbackRecordPO cashbackRecordPO, Integer pageNum);
}
