package com.dadao.capitalpool.mapper;

import com.dadao.capitalpool.entity.CapitalpoolPO;
import com.dadao.pub.mapper.BaseMapper;

/**
 * Created by NFY on 2017-11-21.
 */
public interface CapitalpoolMapper extends BaseMapper {
    CapitalpoolPO findBymMarketId(Long marketId);
}
