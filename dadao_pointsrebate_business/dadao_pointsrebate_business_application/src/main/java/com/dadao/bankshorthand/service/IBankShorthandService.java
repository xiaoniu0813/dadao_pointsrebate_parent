package com.dadao.bankshorthand.service;

import com.dadao.bankshorthand.entity.BankShorthand;
import com.dadao.bankshorthand.entity.BankShorthandVO;

/**
 * Created by NFY on 2017-10-25.
 */
public interface IBankShorthandService {

    BankShorthandVO findByShorthand(BankShorthand bankShorthand);
}
