package com.dadao.cashback.service.impl;

import com.dadao.activities.cashback.activity.CashbackDetailsActivity;
import com.dadao.cashback.service.ICashbackDetailsService;
import com.dadao.cashback.entity.CashbackDetailsVO;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CashbackDetailsServiceImpl implements ICashbackDetailsService {

    @Autowired
    private CashbackDetailsActivity cashbackDetailsActivity;

    public QueryResult findByStatus(CashbackDetailsVO cashbackDetailsVO, String token , Integer pageNum) {
        return cashbackDetailsActivity.findByStatus(cashbackDetailsVO, token,pageNum);
    }

    public Map findMessageByStatus(CashbackDetailsVO cashbackDetailsVO, String token) {
        return cashbackDetailsActivity.findMessageByStatus(cashbackDetailsVO, token);
    }


}
