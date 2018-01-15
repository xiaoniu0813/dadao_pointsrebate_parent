package com.dadao.trade;

import com.dadao.capitalpool.entity.CapitalpoolPO;
import com.dadao.capitalpool.entity.CapitalpoolRecordPO;
import com.dadao.common.BaseTest;
import com.dadao.trade.mapper.TradeMapper;
import com.dadao.user.entity.UserIntegralRecordingPO;
import com.dadao.user.entity.UserTransactionRecodPO;
import org.apache.ibatis.annotations.Param;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by YunQiang on 2017/11/27
 * @author YunQiang
 */
@Transactional
public class TradeTest extends BaseTest{

    @Autowired
    private TradeMapper tradeMapper;

    @Before
    public void init(){

    }

    /**
     * 消减用户对应市场等级的积分
     * @param marketId 积分所在的市场id
     * @param userId 用户userId
     * @param minusIntegral 减少积分额度
     * @return
     */
    @Test
    public void minusUserIntegral(){
        tradeMapper.minusUserIntegral(21L, 149L, new BigDecimal(10));
    }

    /**
     * 消减商户钱包
     * @param userId 商户的userId
     * @param minusMoney 消减金额大小
     * @return
     */
    @Test
    public void minusUserWallet(){
        tradeMapper.minusUserWallet(127L, new BigDecimal(10));
    }

    /**
     *消减资金池余额
     * @param capitalpoolPO 资金池表对应实体类
     * @return
     */
    @Test
    public void minusCapitalpool(){
        CapitalpoolPO capitalpoolPO = new CapitalpoolPO();
        capitalpoolPO.setId(1L);
        capitalpoolPO.setMoney(new BigDecimal(100));
        tradeMapper.minusCapitalpool(capitalpoolPO);
    }


}
