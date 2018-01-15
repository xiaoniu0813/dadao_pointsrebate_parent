package com.dadao.user.mapper;

import com.dadao.pub.mapper.BaseMapper;

/**
 * Created by NFY on 2017-08-02.
 */
public interface IUserTransactionRecodMapper extends BaseMapper {
    /**
     * 查询当月交易数量（付款、退款）
     * @param userId
     * @return
     */
    Integer findMonthTransactionCount(Long userId);
}
