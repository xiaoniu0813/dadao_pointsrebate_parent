package com.dadao.user.service.impl;

import com.dadao.activities.user.activity.UserWalletActivity;
import com.dadao.cashback.entity.BalanceAndFreeze;
import com.dadao.user.service.IUserWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户余额
 *
 * @auther NFY niufuyang
 * @create 2017-08-07
 */
@Service
public class UserWalletServiceImpl implements IUserWalletService {
    @Autowired
    private UserWalletActivity userWalletActivity;

    public BalanceAndFreeze findBalance(String token) {
        return userWalletActivity.findBalance(token);
    }
}
