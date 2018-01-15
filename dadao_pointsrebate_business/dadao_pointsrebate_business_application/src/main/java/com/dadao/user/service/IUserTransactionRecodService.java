package com.dadao.user.service;

import com.dadao.user.entity.UserTransactionRecodPO;
import com.dadao.user.entity.UserTransactionRecodVO;
import com.dadao.utils.QueryResult;

/**
 * Created by NFY on 2017-08-16.
 */
public interface IUserTransactionRecodService {
    QueryResult listTransactionRecord(UserTransactionRecodPO userTransactionRecodPO);
    UserTransactionRecodVO infoTransactionRecord(Long transactionId);
}
