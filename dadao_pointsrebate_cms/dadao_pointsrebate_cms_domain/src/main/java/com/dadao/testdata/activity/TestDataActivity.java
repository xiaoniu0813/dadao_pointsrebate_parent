package com.dadao.testdata.activity;


import com.dadao.shop.entity.Shop;
import com.dadao.shop.mapper.ShopMapper;
import com.dadao.user.entity.*;
import com.dadao.user.mapper.*;
import com.dadao.utils.ArithUtil;
import com.dadao.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * 生成测试数据专用类
 *
 * @auther NFY niufuyang
 * @create 2017-9-27
 */
@Repository
public class TestDataActivity {
    @Autowired
    private UserAccountMapper userAccountMapper;
    @Autowired
    private IUserIntegralMapper iUserIntegralMapper;
    @Autowired
    private UserWalletMapper userWalletMapper;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private IUserIntegralMapper userIntegralMapper;
    @Autowired
    private UserIntegralRecordingMapper userIntegralRecordingMapper;
    @Autowired
    private UserTransactionRecodMapper userTransactionRecodMapper;

    /**
     * 生成并插入用户记录
     */
    public void testUserData() {
        this.addUserInTime();
    }

    /**
     * 生成并插入用户交易记录
     */
    public void tesTransactionRecordData() {
        this.addUserTransactionRecord();
    }

    /**
     * 每30秒钟增加5-10个用户
     */
    @Transactional
    public void addUserInTime() {
        //用户账户
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(2001L);
        Long last = userAccountMapper.findByLastRecord();
        long len = userAccountMapper.findCount(userAccount);
        if (len < 100000L) {
            //[0,1) * 6 + 5 5 - 10之间的随机数
            int a = (int) (Math.random() * 6 + 5);
            //用户积分表
            UserIntegral userIntegral = new UserIntegral();
            //用户钱包
            UserWalletPO userWalletPO = new UserWalletPO();
            for (int i = 1; i <= a; i++) {
                //手机号
                userAccount.setPhone("11111111111");
                //密码
                userAccount.setPassword(EncryptUtil.getEncodeStr((Math.random() + 1) * 10000000 + ""));
                //支付密码
                userAccount.setPayPassword(EncryptUtil.getEncodeStr((Math.random() + 1) * 10000000 + ""));
                //手势密码
                userAccount.setGesturePassword(EncryptUtil.getEncodeStr((Math.random() + 1) * 10000000 + ""));
                //用户token
                userAccount.setToken(EncryptUtil.getEncodeStr((Math.random() + 1) * 10000000 + ""));
                //状态
                userAccount.setStatus(1);
                //是否是商户
                userAccount.setMerchant(0);
                userAccount.setUserId(last + i);
                userAccountMapper.save(userAccount);

                //用户积分
                userIntegral.setIntegral(0d);
                //市场等级
                userIntegral.setMarketId(21L);
                //用户id
                userIntegral.setUserId(last + i);
                iUserIntegralMapper.save(userIntegral);
                //用户余额
                userWalletPO.setBalance(BigDecimal.valueOf(0));
                //商户保证金
                userWalletPO.setMargin(BigDecimal.valueOf(0));
                //状态
                userWalletPO.setStatus(1);
                //用户id
                userWalletPO.setUserId(last + i);
                userWalletMapper.save(userWalletPO);
            }
        }
    }

    /**
     * 每15秒增加5-10条用户交易记录
     */
    //@Transactional
    public void addUserTransactionRecord() {
        Random random = new Random();
        //生成5-10之间的随机数 start
        //先生成0-9之间的随机数
        //int TEN_TEENTY_TR = random.nextInt(11) + 5;
        //int TEN_TEENTY_TR=100;
        //生成5-10之间的随机数 end

        //查询最大userID
        Long maxUserID = 102000L;
        //int j=0;
        for (int i = 0; i < 200000; i++) {
            //生成2001-最大用户ID之间的随机数 start
            int userID = random.nextInt(maxUserID.intValue()-2000) + 2001;
            //System.out.println("userID：" + userID);
            //生成2001-最大用户ID之间的随机数 end

            //模拟用户消费 start
            //随机产生一个店铺去消费（店铺ID在1001-2000）
            //生成1001-2000之间随机数 start
            int shopID = random.nextInt(1000) + 1001;
            //System.out.println("shopID：" + shopID);
            //生成1001-2000之间随机数 end

            //随机数生成1-1000之间的随机数作为本次消费 start
            DecimalFormat dcmFmt = new DecimalFormat("0.00");
            Random rand = new Random();
            double money = Double.valueOf(dcmFmt.format(rand.nextDouble() * 1000));
            //System.out.println("本次消费金额：" + money);
            //随机数生成1-1000之间的随机数作为本次消费 end

            //根据店铺ID查询店铺积分率
            Shop shop = (Shop) shopMapper.findById(shopID);

            //生成用户积分 start
            UserIntegral userIntegral = new UserIntegral();
            userIntegral.setUserId((long) userID);
            userIntegral.setMarketId(21L);
            userIntegral = userIntegralMapper.findUserIntegralByMarketId(userIntegral);
            double integral=money*shop.getIntegralRate()/100;
            double integral_format = Double.valueOf(String.format("%.2f", integral-0.005));
            //System.out.println("积分："+integral_format);
            userIntegral.setIntegral(userIntegral.getIntegral() + integral_format);
            userIntegralMapper.update(userIntegral);
            //生成用户积分明细
            UserIntegralRecordingPO userIntegralRecordingPO=new UserIntegralRecordingPO();
            userIntegralRecordingPO.setUserId(userIntegral.getUserId());
            userIntegralRecordingPO.setDirection(0);
            userIntegralRecordingPO.setMarketId(21L);
            userIntegralRecordingPO.setIntegral(new BigDecimal(integral_format));
            userIntegralRecordingMapper.save(userIntegralRecordingPO);
            //生成用户交易记录
            UserTransactionRecodPO userTransactionRecodPO=new UserTransactionRecodPO();
            userTransactionRecodPO.setUserId(userIntegral.getUserId());
            userTransactionRecodPO.setTransactionAmount(new BigDecimal(money));
            userTransactionRecodPO.setOtherAccount(userIntegralRecordingPO.getIRid()+"");
            userTransactionRecodMapper.save(userTransactionRecodPO);
            System.out.println("执行了："+i+"条数据！");
        }
    }
}
