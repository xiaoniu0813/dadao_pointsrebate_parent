package com.dadao.user;

import com.dadao.pub.BaseTest;
import com.dadao.user.mapper.UserWalletMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by YunQiang on 2017/10/27
 */
public class UserWalletMapperTest extends BaseTest{

    @Autowired
    private UserWalletMapper userWalletMapper;

    @Test
    public void updateUserWalletByUserId(){
        userWalletMapper.updateUserWalletByUserId(5L, new BigDecimal(10));
    }

}
