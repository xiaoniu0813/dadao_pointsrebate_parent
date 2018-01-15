package com.dadao.user.service;

import com.dadao.user.entity.UserTransactionRecodPO;
import com.dadao.utils.POPage;

import java.util.List;

public interface UserTransactionRecodService {

    public POPage showUserTransactionRecodPOByPage(UserTransactionRecodPO userTransactionRecodPO);

    public List<UserTransactionRecodPO> findByEntity(UserTransactionRecodPO userTransactionRecodPO);
}
