package com.dadao.transaction.service.impl;

import com.dadao.transaction.activity.TransactionActivity;
import com.dadao.transaction.entity.TransactionRecord;
import com.dadao.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionActivity transactionActivity;

    public List<TransactionRecord> findByEntity(Long userId) {
        return transactionActivity.findByEntity(userId);
    }

    public TransactionRecord findById(Long transactionId) {
        return transactionActivity.findById(transactionId);
    }
}
