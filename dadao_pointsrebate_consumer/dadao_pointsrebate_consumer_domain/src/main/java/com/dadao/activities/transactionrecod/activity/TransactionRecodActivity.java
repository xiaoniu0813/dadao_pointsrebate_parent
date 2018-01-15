package com.dadao.activities.transactionrecod.activity;

import com.dadao.user.entity.UserAccount;
import com.dadao.user.entity.UserTransactionRecodPO;
import com.dadao.user.mapper.IUserTransactionRecodMapper;
import com.dadao.user.mapper.UserMapper;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户交易记录详细
 *
 * @auther NFY niufuyang
 * @create 2017-08-02
 */
@Repository
public class TransactionRecodActivity {
    @Autowired
    private IUserTransactionRecodMapper userTransactionRecodMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 查询用户余额明细
     * @param userTransactionRecodPO
     * @param token
     * @return
     */
    public QueryResult findUserBalanceRecod(UserTransactionRecodPO userTransactionRecodPO,String token){
        UserAccount account=new UserAccount();
        account.setToken(token);
        account=userMapper.findByToken(account);
        if (account==null){
            return null;
        }
        userTransactionRecodPO.setUserId(account.getUserId());
        userTransactionRecodPO.setPayMethod(4);
        QueryResult queryResult = new QueryResult();
        Long totalSize = userTransactionRecodMapper.findCount(userTransactionRecodPO);
        List list = userTransactionRecodMapper.findByPage(userTransactionRecodPO);
        Long totalPage = totalSize % userTransactionRecodPO.getPageSize() == 0 ? totalSize / userTransactionRecodPO.getPageSize() : totalSize / userTransactionRecodPO.getPageSize() + 1;
        queryResult.setTotalPage(totalPage);
        queryResult.setTotalSize(totalSize);
        queryResult.setList(list);
        return queryResult;

    }
}
