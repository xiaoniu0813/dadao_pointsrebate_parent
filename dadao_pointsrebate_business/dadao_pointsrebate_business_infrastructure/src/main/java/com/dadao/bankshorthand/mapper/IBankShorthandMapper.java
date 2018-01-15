package com.dadao.bankshorthand.mapper;

import com.dadao.bankshorthand.entity.BankShorthand;
import com.dadao.bankshorthand.entity.BankShorthandVO;
import com.dadao.pub.mapper.BaseMapper;

/**
 * Created by NFY on 2017-10-25.
 */
public interface IBankShorthandMapper extends BaseMapper {
    BankShorthandVO findByShorthand(String shorthand);
}
