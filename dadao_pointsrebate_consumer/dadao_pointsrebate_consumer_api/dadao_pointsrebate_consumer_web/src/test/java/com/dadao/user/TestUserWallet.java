package com.dadao.user;

import com.dadao.common.BaseTest;
import com.dadao.user.entity.UserWalletPO;
import com.dadao.user.entity.UserWalletVO;
import com.dadao.user.mapper.UserWalletMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by YunQiang on 2017/8/7
 */
public class TestUserWallet extends BaseTest{

    @Autowired
    private UserWalletMapper userWalletMapper;

    private UserWalletVO userWalletVO;

    @Before
    public void Init(){
        userWalletVO = new UserWalletVO();
        userWalletVO.setUserId(2L);
        userWalletVO.setStatus(1);
    }

    @Test
    public void save(){
        userWalletMapper.save(userWalletVO);
    }


}
