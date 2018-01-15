package com.dadao.user.service;

import com.dadao.user.entity.UserWalletVO;

/**
 * Created by NFY on 2017-08-11.
 */
public interface IUserWalloetService {
    UserWalletVO findUserWallet(Long userId);
}
