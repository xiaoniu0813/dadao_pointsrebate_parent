package com.dadao.shop.mapper;

import com.dadao.pub.mapper.BaseMapper;
import com.dadao.shop.entity.StartupShop;

/**
 * Created by yangrui on 2017/7/16.
 */
public interface RecommendPositionMapper extends BaseMapper {

    StartupShop findStartupShop();

}
