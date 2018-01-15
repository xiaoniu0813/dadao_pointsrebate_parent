package com.dadao.withdraw.service;

import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;
import com.dadao.withdraw.entity.WithdrawApplicationPO;
import com.dadao.withdraw.entity.WithdrawList;

/**
 * Created by NFY on 2017-08-08.
 */
public interface IWithdrawApplicationService {
    QueryResult findWithdrawList(WithdrawList withdrawList,String token);

    Result withdrawApplication(WithdrawApplicationPO withdrawApplicationPO, String token,String payPassword);
}
