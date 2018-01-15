package com.dadao.user.service.impl;

import com.dadao.user.activity.UserWalloetActivity;
import com.dadao.user.entity.UserWalletVO;
import com.dadao.user.service.IUserWalloetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 钱包实现类
 *
 * @auther NFY niufuyang
 * @create 2017-08-11
 */
@Service
public class UserWalloetServiceImpl implements IUserWalloetService {
    @Autowired
    private UserWalloetActivity userWalloetActivity;

    public UserWalletVO findUserWallet(Long userId) {
        return userWalloetActivity.findUserWallet(userId);
    }
}
