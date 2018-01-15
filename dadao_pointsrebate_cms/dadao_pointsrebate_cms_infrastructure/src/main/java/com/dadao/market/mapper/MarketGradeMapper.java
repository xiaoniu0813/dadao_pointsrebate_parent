package com.dadao.market.mapper;

import com.dadao.market.entity.MarketGradePO;
import com.dadao.pub.mapper.BaseMapper;

/**
 * 市场等级表
 * Created by GUOYU on 2017/7/25.
 */
public interface MarketGradeMapper extends BaseMapper {

    MarketGradePO findByMarketId(Long marketId);

}
