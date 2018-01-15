package com.dadao.transaction;

import com.dadao.common.BaseTest;
import com.dadao.transaction.entity.TransactionRecord;
import com.dadao.transaction.mapper.TransactionRecordMapper;
import com.dadao.transaction.service.TransactionService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TransactionTest extends BaseTest {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRecordMapper transactionRecordMapper;

    @Test
    public void testFindByEntity() {
        List<TransactionRecord> list = transactionService.findByEntity(1L);
        for (TransactionRecord transactionRecord : list) {
            System.out.println(transactionRecord);
        }
    }

    @Test
    public void testFindById(){
        System.out.println((TransactionRecord)transactionRecordMapper.findById(1L));
    }
}
