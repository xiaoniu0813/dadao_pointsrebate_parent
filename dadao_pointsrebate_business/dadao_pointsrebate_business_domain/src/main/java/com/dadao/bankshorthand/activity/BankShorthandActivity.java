package com.dadao.bankshorthand.activity;

import com.dadao.bankshorthand.entity.BankShorthand;
import com.dadao.bankshorthand.entity.BankShorthandVO;
import com.dadao.bankshorthand.mapper.IBankShorthandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @auther NFY niufuyang
 * @create 2017-10-25
 */
@Repository
public class BankShorthandActivity {
    @Autowired
    private IBankShorthandMapper mapper;

    /**
     * 根据英文缩写查询银行名称
     * @param bankShorthand
     * @return
     */
    public BankShorthandVO findByShorthand(BankShorthand bankShorthand){
        BankShorthandVO bankShorthandRelust=mapper.findByShorthand(bankShorthand.getShorthand());
        bankShorthandRelust.setCardType(bankShorthand.getCardType());
        return bankShorthandRelust;
    }
}
