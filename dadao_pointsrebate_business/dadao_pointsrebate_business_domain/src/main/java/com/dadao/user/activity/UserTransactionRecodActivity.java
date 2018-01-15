package com.dadao.user.activity;

import com.dadao.user.entity.UserTransactionRecodPO;
import com.dadao.user.entity.UserTransactionRecodVO;
import com.dadao.user.mapper.IUserTransactionRecodMapper;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 交易流水
 *
 * @auther NFY niufuyang
 * @create 2017-08-16
 */
@Repository
public class UserTransactionRecodActivity {
    @Autowired
    private IUserTransactionRecodMapper mapper;

    /**
     * 查询商户端用户交易记录
     *
     * @param userTransactionRecodPO
     * @return
     */
    public QueryResult listTransactionRecord(UserTransactionRecodPO userTransactionRecodPO) {
        QueryResult queryResult = new QueryResult();
        Long totalSize = mapper.findCount(userTransactionRecodPO);
        List list = mapper.findByPage(userTransactionRecodPO);
        Long totalPage = totalSize % userTransactionRecodPO.getPageSize() == 0 ? totalSize / userTransactionRecodPO.getPageSize() : totalSize / userTransactionRecodPO.getPageSize() + 1;
        queryResult.setTotalPage(totalPage);
        queryResult.setTotalSize(totalSize);
        queryResult.setList(list);
        return queryResult;
    }

    /**
     * 查询交易记录详情
     * @param transactionId
     * @return
     */
    public UserTransactionRecodVO infoTransactionRecord(Long transactionId) {
        return (UserTransactionRecodVO)mapper.findById(transactionId);
    }
}
