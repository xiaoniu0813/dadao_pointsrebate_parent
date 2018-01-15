package com.dadao.transaction.service;

import com.dadao.transaction.entity.TransactionRecord;

import java.util.List;

public interface TransactionService {

    List<TransactionRecord> findByEntity(Long userId);

    TransactionRecord findById(Long transactionId);
}
