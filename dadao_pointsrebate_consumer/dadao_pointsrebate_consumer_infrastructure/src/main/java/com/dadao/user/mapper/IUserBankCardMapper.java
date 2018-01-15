package com.dadao.user.mapper;

import com.dadao.pub.mapper.BaseMapper;
import com.dadao.user.entity.UserBankCardPO;

import java.util.List;

/**
 * Created by NFY on 2017-08-07.
 */
public interface IUserBankCardMapper extends BaseMapper {
    List findByUserId(UserBankCardPO userBankCardPO);
}
