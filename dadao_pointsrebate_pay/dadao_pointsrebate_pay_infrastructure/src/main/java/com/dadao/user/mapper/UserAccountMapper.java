package com.dadao.user.mapper;

import com.dadao.pub.mapper.BaseMapper;
import com.dadao.user.entity.UserAccount;

/**
 * Created by NFY on 2017-11-8.
 */
public interface UserAccountMapper extends BaseMapper {
    UserAccount findUserByToken(String token);
}
