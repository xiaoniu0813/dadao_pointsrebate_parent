package com.dadao.capitalpool.mapper;

import com.dadao.capitalpool.entity.CapitalpoolPO;
import com.dadao.capitalpool.entity.CapitalpoolVO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * Created by NFY on 2017-8-24.
 */
public interface ICapitalpoolMapper {

    CapitalpoolVO findByMarketId(Long marketId);

    int updateMoneyByMarketId(CapitalpoolPO capitalpoolPO);

    /**
     * 消减资金池待返金额
     * @param id    资金池主键
     * @param money 消减金额大小
     * @return
     */
    int minusCapitalpoolById(@Param("id") Long id, @Param("money")BigDecimal money);
}
