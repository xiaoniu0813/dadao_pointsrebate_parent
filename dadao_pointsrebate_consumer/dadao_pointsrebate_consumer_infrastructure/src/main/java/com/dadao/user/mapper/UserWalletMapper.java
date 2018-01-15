package com.dadao.user.mapper;

import com.dadao.cashback.entity.BalanceAndFreeze;
import com.dadao.pub.mapper.BaseMapper;
import com.dadao.user.entity.UserWalletPO;
import com.dadao.user.entity.UserWalletVO;

/**
 * Created by NFY on 2017-08-07.
 */
public interface UserWalletMapper extends BaseMapper {
    BalanceAndFreeze findBalance(String token);
}
