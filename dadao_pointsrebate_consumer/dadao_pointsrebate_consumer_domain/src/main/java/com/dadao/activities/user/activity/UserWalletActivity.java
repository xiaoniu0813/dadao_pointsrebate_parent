package com.dadao.activities.user.activity;

import com.dadao.cashback.entity.BalanceAndFreeze;
import com.dadao.user.entity.UserWalletPO;
import com.dadao.user.mapper.UserWalletMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * 用户余额表操作
 *
 * @auther NFY niufuyang
 * @create 2017-08-07
 */
@Repository
public class UserWalletActivity {
    @Autowired
    private UserWalletMapper userWalletMapper;

    /**
     * 根据token查询用户余额、冻结金额----niufy
     *
     * @param token
     * @return
     */
    public BalanceAndFreeze findBalance(String token) {
        BalanceAndFreeze balanceAndFreeze= userWalletMapper.findBalance(token);
        if(balanceAndFreeze==null){
            balanceAndFreeze=new BalanceAndFreeze();
            balanceAndFreeze.setBalance(new BigDecimal(0));
        }
        return balanceAndFreeze;
    }

    /**
     * 更新账户余额信息----niufy
     * @param userWalletPO
     * @return
     */
    public int updateUserWallet(UserWalletPO userWalletPO){
        return userWalletMapper.update(userWalletPO);
    }
}
