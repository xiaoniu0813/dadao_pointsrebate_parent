package com.dadao.bankshorthand.mapper;

import com.dadao.pub.mapper.BaseMapper;

import java.util.List;

/**
 * Created by NFY on 2017-11-13.
 */
public interface IBankShorthandMapper extends BaseMapper {
    List findBankList();
}
