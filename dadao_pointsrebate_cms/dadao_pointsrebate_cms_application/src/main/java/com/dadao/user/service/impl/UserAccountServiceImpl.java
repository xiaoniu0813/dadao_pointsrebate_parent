package com.dadao.user.service.impl;

import com.dadao.user.activity.UserAccountActivity;
import com.dadao.user.entity.UserAccount;
import com.dadao.user.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by NFY on 2017-07-16.
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    private UserAccountActivity activity;


    public String findByLogin(UserAccount userAccount) {
        return activity.findByLogin(userAccount);
    }

    public boolean saveUser(UserAccount userAccount){
        return activity.saveUser(userAccount);
    }
}
