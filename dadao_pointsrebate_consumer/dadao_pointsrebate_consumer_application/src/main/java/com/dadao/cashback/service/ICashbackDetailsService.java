package com.dadao.cashback.service;

import com.dadao.activities.cashback.activity.CashbackDetailsActivity;
import com.dadao.cashback.entity.CashbackDetailsVO;
import com.dadao.utils.QueryResult;

import java.util.List;
import java.util.Map;

public interface ICashbackDetailsService {

    public QueryResult findByStatus(CashbackDetailsVO cashbackDetailsVO, String token , Integer pageNum);

    public Map findMessageByStatus(CashbackDetailsVO cashbackDetailsVO, String token);

}
