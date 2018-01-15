package com.dadao.balance;

import com.dadao.balance.service.IBalanceService;
import com.dadao.common.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by YunQiang on 2017/12/7
 */
public class BalanceTest extends BaseTest{

    @Autowired
    private IBalanceService balanceService;

    @Test
    public void queryBusinessBalance(){
        System.out.println(balanceService.queryBusinessBalance("6d1442e93cbab8bb9b9d14fe3aa012d1"));
    }

}
