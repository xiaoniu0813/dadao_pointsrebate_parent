package com.dadao.bankshorthand.service.impl;

import com.dadao.bankshorthand.activity.BankShorthandActivity;
import com.dadao.bankshorthand.service.IBankShorthandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther NFY niufuyang
 * @create 2017-11-13
 */
@Service
public class IBankShorthandServiceImpl implements IBankShorthandService {

    @Autowired
    private BankShorthandActivity bankShorthandActivity;

    @Override
    public List findBankList() {
        return bankShorthandActivity.findBankList();
    }
}
