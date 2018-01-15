package com.dadao.user.mapper;

import com.dadao.pub.mapper.BaseMapper;
import com.dadao.user.entity.UserWalletVO;

/**
 * Created by NFY on 2017-08-11.
 */
public interface IUserWalletMapper extends BaseMapper {
    UserWalletVO findUserWallet(Long uiserId);
}
