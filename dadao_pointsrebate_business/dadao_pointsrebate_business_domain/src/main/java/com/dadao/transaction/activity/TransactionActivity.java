package com.dadao.transaction.activity;

import com.dadao.transaction.entity.TransactionRecord;
import com.dadao.transaction.mapper.TransactionRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionActivity {

    @Autowired
    private TransactionRecordMapper transactionRecordMapper;

    /**
     * @Author: yangrui
     * @Description: 查询商铺收款记录
     * @Date: 下午5:29 2017/7/30
     */
    public List<TransactionRecord> findByEntity(Long userId) {
        return transactionRecordMapper.findByEntity(userId);
    }

    /**
     * @Author: yangrui
     * @Description:查询商铺收款记录详情
     * @Date: 下午2:31 2017/8/13
     */
    public TransactionRecord findById(Long transactionId) {
        return (TransactionRecord) transactionRecordMapper.findById(transactionId);
    }
}
