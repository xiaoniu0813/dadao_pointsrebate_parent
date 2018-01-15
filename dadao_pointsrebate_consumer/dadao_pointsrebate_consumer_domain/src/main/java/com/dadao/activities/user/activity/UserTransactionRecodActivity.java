package com.dadao.activities.user.activity;

import com.dadao.user.mapper.IUserTransactionRecodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 用户消费记录
 *
 * @auther NFY niufuyang
 * @create 2017-08-09
 */
@Repository
public class UserTransactionRecodActivity {
    @Autowired
    private IUserTransactionRecodMapper userTransactionRecodMapper;
}
