package com.dadao.transactionrecod.service.impl;

import com.dadao.activities.transactionrecod.activity.TransactionRecodActivity;
import com.dadao.common.service.impl.BaseService;
import com.dadao.transactionrecod.service.ITransactionRecodService;
import com.dadao.user.entity.UserTransactionRecodPO;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户交易记录
 *
 * @auther NFY niufuyang
 * @create 2017-08-02
 */
@Service
public class TransactionRecodServiceImpl extends BaseService implements ITransactionRecodService {
    @Autowired
    private TransactionRecodActivity transactionRecodActivity;

    public QueryResult findUserBalanceRecod(UserTransactionRecodPO userTransactionRecodPO, String token) {
        return transactionRecodActivity.findUserBalanceRecod(userTransactionRecodPO,token);
    }
}
