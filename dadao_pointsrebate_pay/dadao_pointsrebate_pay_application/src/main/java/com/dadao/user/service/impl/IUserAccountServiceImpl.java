package com.dadao.user.service.impl;

import com.dadao.user.entity.UserAccount;
import com.dadao.user.mapper.UserAccountMapper;
import com.dadao.user.service.IUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther NFY niufuyang
 * @create 2017-11-8
 */
@Service
public class IUserAccountServiceImpl implements IUserAccountService {
    @Autowired
    private UserAccountMapper mapper;

    public UserAccount findByUserId(Long userId) {
        return (UserAccount) mapper.findById(userId);
    }
}
