package com.dadao.bankshorthand.service.impl;

import com.dadao.bankshorthand.activity.BankShorthandActivity;
import com.dadao.bankshorthand.entity.BankShorthand;
import com.dadao.bankshorthand.entity.BankShorthandVO;
import com.dadao.bankshorthand.service.IBankShorthandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther NFY niufuyang
 * @create 2017-10-25
 */
@Service
public class IBankShorthandServiceImpl implements IBankShorthandService {
    @Autowired
    private BankShorthandActivity bankShorthandActivity;

    public BankShorthandVO findByShorthand(BankShorthand bankShorthand) {
        return bankShorthandActivity.findByShorthand(bankShorthand);
    }
}
