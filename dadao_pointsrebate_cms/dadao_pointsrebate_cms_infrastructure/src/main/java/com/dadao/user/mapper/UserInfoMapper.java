package com.dadao.user.mapper;

import com.dadao.pub.mapper.BaseMapper;

public interface UserInfoMapper extends BaseMapper{

    Object findUser(Object entity);
    Long findCountByBankCard(Long userId);
}
