package com.dadao.user.mapper;

import com.dadao.pub.mapper.BaseMapper;
import com.dadao.user.entity.UserRole;

/**
 * Created by NFY on 2017-07-17.
 */
public interface UserRoleMapper extends BaseMapper {
    UserRole findByUserId(UserRole userRole);
}
