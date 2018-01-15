package com.dadao.user.service.impl;

import com.dadao.user.activity.UserTransactionRecodActivity;
import com.dadao.user.entity.UserTransactionRecodPO;
import com.dadao.user.entity.UserTransactionRecodVO;
import com.dadao.user.service.IUserTransactionRecodService;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 交易流水
 *
 * @auther NFY niufuyang
 * @create 2017-08-16
 */
@Service
public class UserTransactionRecodServiceImpl implements IUserTransactionRecodService {
    @Autowired
    private UserTransactionRecodActivity activity;
    public QueryResult listTransactionRecord(UserTransactionRecodPO userTransactionRecodPO) {
        return activity.listTransactionRecord(userTransactionRecodPO);
    }

    public UserTransactionRecodVO infoTransactionRecord(Long transactionId) {
        return activity.infoTransactionRecord(transactionId);
    }
}
