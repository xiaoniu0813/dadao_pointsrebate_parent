package com.dadao.cash;

import com.dadao.cashback.activity.CashbackActivity;
import com.dadao.pub.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by YunQiang on 2017/11/1
 */
public class CashbackActivityTest extends BaseTest{

    @Autowired
    private CashbackActivity cashbackActivity;

    @Transactional
    @Test
    @Description("测试消减用户积分操作(方法私有化)")
    public void minusUserIntegral(){
        //cashbackActivity.minusUserIntegral(117L,21L,2L,2L,new BigDecimal(1));
    }

    @Test
    @Transactional
    @Description("测试消减资金池金额的操作(方法私有化)")
    public void minusCapitalpool(){
        //cashbackActivity.minusCapitalpool(1L,177L,21L, new BigDecimal(200));
    }

    @Test
    @Transactional
    @Description("测试增加用户钱包金额(方法私有化)")
    public void addUserWallet(){
        //cashbackActivity.addUserWallet(117L, new BigDecimal(200));
    }

    @Test
    @Transactional
    @Description("测试给单个商户返利")
    public void cashToBusiness(){
        cashbackActivity.cashToBusiness(198113L,177L, new BigDecimal(7.5));
    }

}
