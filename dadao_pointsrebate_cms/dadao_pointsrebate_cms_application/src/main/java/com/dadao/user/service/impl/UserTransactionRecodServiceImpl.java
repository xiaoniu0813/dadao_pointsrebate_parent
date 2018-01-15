package com.dadao.user.service.impl;

import com.dadao.user.activity.UserTransactionRecodActivity;
import com.dadao.user.entity.UserTransactionRecodPO;
import com.dadao.user.service.UserTransactionRecodService;
import com.dadao.utils.POPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTransactionRecodServiceImpl implements UserTransactionRecodService {
    @Autowired
    private UserTransactionRecodActivity userTransactionRecodActivity;


    public POPage showUserTransactionRecodPOByPage(UserTransactionRecodPO userTransactionRecodPO) {
        return userTransactionRecodActivity.showUserTransactionRecodPOByPage(userTransactionRecodPO);
    }

    public List<UserTransactionRecodPO> findByEntity(UserTransactionRecodPO userTransactionRecodPO) {
        return userTransactionRecodActivity.findByEntity(userTransactionRecodPO);
    }
}
