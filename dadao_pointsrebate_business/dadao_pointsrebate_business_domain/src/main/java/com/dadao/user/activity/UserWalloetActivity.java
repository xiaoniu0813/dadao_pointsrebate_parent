package com.dadao.user.activity;

import com.dadao.user.entity.UserWalletVO;
import com.dadao.user.mapper.IUserWalletMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 商户查询余额
 *
 * @auther NFY niufuyang
 * @create 2017-08-11
 */
@Repository
public class UserWalloetActivity {
    @Autowired
    private IUserWalletMapper iUserWalletMapper;

    public UserWalletVO findUserWallet(Long userId){
        return iUserWalletMapper.findUserWallet(userId);
    }
}
