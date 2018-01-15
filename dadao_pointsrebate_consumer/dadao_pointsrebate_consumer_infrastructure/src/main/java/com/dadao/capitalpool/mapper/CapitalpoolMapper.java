package com.dadao.capitalpool.mapper;

import com.dadao.capitalpool.entity.CapitalpoolVO;
import com.dadao.pub.mapper.BaseMapper;

/**
 * Created by NFY on 2017-10-11.
 */
public interface CapitalpoolMapper extends BaseMapper {
    CapitalpoolVO findBymMarketId(Long marketId);
}
