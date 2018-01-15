package com.dadao.user.mapper;

import com.dadao.pub.mapper.BaseMapper;
import com.dadao.user.entity.UserIntegral;

/**
 * Created by NFY on 2017-11-20.
 */
public interface IUserIntegralMapper extends BaseMapper {
    UserIntegral findByUserId(UserIntegral userIntegral);
}
