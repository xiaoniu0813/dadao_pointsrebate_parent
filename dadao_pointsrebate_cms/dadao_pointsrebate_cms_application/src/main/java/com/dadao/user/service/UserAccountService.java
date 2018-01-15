package com.dadao.user.service;

import com.dadao.user.entity.UserAccount;

/**
 * Created by NFY on 2017-07-16.
 */
public interface UserAccountService {
    String findByLogin(UserAccount userAccount);

    boolean saveUser(UserAccount userAccount);
}
