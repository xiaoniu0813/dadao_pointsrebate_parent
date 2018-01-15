package com.dadao.withdraw.service.impl;

import com.dadao.activities.withdraw.activity.WithdrawApplicationActivity;
import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;
import com.dadao.withdraw.entity.WithdrawApplicationPO;
import com.dadao.withdraw.entity.WithdrawList;
import com.dadao.withdraw.service.IWithdrawApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 提现申请
 *
 * @auther NFY niufuyang
 * @create 2017-08-08
 */
@Service
public class WithdrawApplicationServiceImpl implements IWithdrawApplicationService {
    @Autowired
    private WithdrawApplicationActivity withdrawApplicationActivity;

    public QueryResult findWithdrawList(WithdrawList withdrawList, String token) {
        return withdrawApplicationActivity.findWithdrawList(withdrawList,token);
    }

    public Result withdrawApplication(WithdrawApplicationPO withdrawApplicationPO, String token,String payPassword) {
        return withdrawApplicationActivity.withdrawApplication(withdrawApplicationPO,token,payPassword);
    }
}
