package com.dadao.user.service;

import com.dadao.utils.QueryResult;

/**
 * Created by NFY on 2017-08-07.
 */
public interface IUserBankCardService {
    QueryResult findUserBankCardList(String token);
}
