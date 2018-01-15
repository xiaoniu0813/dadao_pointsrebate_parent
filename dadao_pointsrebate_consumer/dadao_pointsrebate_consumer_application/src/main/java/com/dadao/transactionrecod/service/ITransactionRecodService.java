package com.dadao.transactionrecod.service;

import com.dadao.user.entity.UserTransactionRecodPO;
import com.dadao.utils.QueryResult;

/**
 * Created by NFY on 2017-08-02.
 */
public interface ITransactionRecodService {
    QueryResult findUserBalanceRecod(UserTransactionRecodPO userTransactionRecodPO,String token);
}
