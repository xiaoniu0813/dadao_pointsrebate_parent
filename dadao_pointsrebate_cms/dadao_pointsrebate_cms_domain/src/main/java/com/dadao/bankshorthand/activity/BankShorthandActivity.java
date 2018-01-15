package com.dadao.bankshorthand.activity;

import com.dadao.bankshorthand.mapper.IBankShorthandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 查询银行信息（对接易宝支付）
 *
 * @auther NFY niufuyang
 * @create 2017-11-13
 */
@Repository
public class BankShorthandActivity {

    @Autowired
    private IBankShorthandMapper mapper;

    public List findBankList(){
        return mapper.findBankList();
    }
}
