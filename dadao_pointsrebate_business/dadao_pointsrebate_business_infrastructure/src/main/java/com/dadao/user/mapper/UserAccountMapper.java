package com.dadao.user.mapper;

import com.dadao.pub.mapper.BaseMapper;
import com.dadao.user.entity.UserAccount;

public interface UserAccountMapper extends BaseMapper {

    UserAccount findByToken(String token);
}
