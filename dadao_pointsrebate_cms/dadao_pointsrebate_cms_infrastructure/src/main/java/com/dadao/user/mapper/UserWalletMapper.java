package com.dadao.user.mapper;

import com.dadao.pub.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * Created by NFY on 2017-8-27.
 */
public interface UserWalletMapper extends BaseMapper {

    /**
     * 增加用户钱包余额
     * @param userId 用户id
     * @param balance 怎加数额
     * @return
     */
    int updateUserWalletByUserId(@Param("userId") long userId, @Param("balance") BigDecimal balance);

}
