package com.dadao.user.service;

import com.dadao.user.entity.UserAccount;

/**
 * Created by NFY on 2017-11-8.
 */
public interface IUserAccountService {
     UserAccount findByUserId(Long userId);
}
