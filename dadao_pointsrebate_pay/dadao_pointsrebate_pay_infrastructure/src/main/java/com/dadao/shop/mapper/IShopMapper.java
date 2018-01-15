package com.dadao.shop.mapper;

import com.dadao.shop.entity.Shop;

/**
 * Created by NFY on 2017-11-15.
 */
public interface IShopMapper {
    Shop findByShopId(Long shopId);
}
