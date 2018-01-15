package com.dadao.testdata;

import com.dadao.testdata.activity.TestDataActivity;
import com.dadao.user.entity.UserAccount;
import com.dadao.user.entity.UserIntegral;
import com.dadao.user.entity.UserWalletPO;
import com.dadao.user.mapper.IUserIntegralMapper;
import com.dadao.user.mapper.UserAccountMapper;
import com.dadao.user.mapper.UserWalletMapper;
import com.dadao.utils.EncryptUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/spring-dao.xml" , "classpath:spring/spring-web.xml", "classpath:spring/spring-service.xml", "classpath:spring/spring-ehcache.xml"})
public class TestData {
    @Autowired
    private IUserIntegralMapper iUserIntegralMapper;
    @Autowired
    private UserWalletMapper userWalletMapper;
    @Autowired
    private TestDataActivity testDataActivity;
    @Autowired
    private UserAccountMapper userAccountMapper;

    /**
     * 添加两万条数据 假数据 包括user_account user_integral user_wallet
     */
    @Test
    @Transactional
    public void addUser(){
        UserAccount userAccount = new UserAccount();
        //手机号
        userAccount.setPhone("11111111111");
        //密码
        userAccount.setPassword(EncryptUtil.getEncodeStr((Math.random()+1)*10000000 + ""));
        //支付密码
        userAccount.setPayPassword(EncryptUtil.getEncodeStr((Math.random()+1)*10000000 + ""));
        //手势密码
        userAccount.setGesturePassword(EncryptUtil.getEncodeStr((Math.random()+1)*10000000 + ""));
        //用户token
        userAccount.setToken(EncryptUtil.getEncodeStr((Math.random()+1)*10000000 + ""));
        //状态
        userAccount.setStatus(1);
        //是否是商户
        userAccount.setMerchant(0);

        UserIntegral userIntegral = new UserIntegral();
        //用户积分
        userIntegral.setIntegral(0d);
        //市场等级
        userIntegral.setMarketId(21L);
        UserWalletPO userWalletPO = new UserWalletPO();

        //用户余额
        userWalletPO.setBalance(BigDecimal.valueOf(0));
        //商户保证金
        userWalletPO.setMargin(BigDecimal.valueOf(0));
        //状态
        userWalletPO.setStatus(1);
        //循环添加
        for(int i = 2001;i<= 22000;i++) {
            userAccount.setUserId(Long.valueOf(i));
            userAccountMapper.save(userAccount);

            userIntegral.setUserId(Long.valueOf(i));
            iUserIntegralMapper.save(userIntegral);

            //用户id
            userWalletPO.setUserId(Long.valueOf(i));
            userWalletMapper.save(userWalletPO);

        }

    }

   //根据时间添加用户
    @Test
    public void addUserInTime(){
        testDataActivity.addUserInTime();
    }

    @Test
    public void addUserTransactionRecord(){
        testDataActivity.addUserTransactionRecord();
    }
}
