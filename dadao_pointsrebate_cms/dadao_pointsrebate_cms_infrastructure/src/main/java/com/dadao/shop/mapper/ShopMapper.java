package com.dadao.shop.mapper;

import com.dadao.pub.mapper.BaseMapper;
import com.dadao.shop.entity.Shop;
import com.dadao.shop.entity.ShopPO;

import java.util.List;

/**
 * Created by yunqiang1 on 2017/7/17.
 */
public interface ShopMapper extends BaseMapper {
    /**
     * 根据商户id查询所有子商户id
     * @param userId
     * @return
     */
    List findByShopId(Long userId);

}
