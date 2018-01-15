package com.dadao.user.service;

import com.dadao.cashback.entity.BalanceAndFreeze;

import java.util.Map;

/**
 * Created by NFY on 2017-08-07.
 */
public interface IUserWalletService {
    BalanceAndFreeze findBalance(String token);
}
