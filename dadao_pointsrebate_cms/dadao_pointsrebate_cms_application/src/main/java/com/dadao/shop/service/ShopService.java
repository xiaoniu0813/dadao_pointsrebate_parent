package com.dadao.shop.service;

import com.dadao.shop.entity.Shop;
import com.dadao.shop.entity.ShopPO;
import com.dadao.utils.QueryResult;

import java.util.List;

/**
 * Created by yunqiang1 on 2017/7/17.
 */

public interface ShopService {

    /**
     * 根据shopId查找店铺
     * @param shopId
     * @return Shop
     */
    public Shop findShopById(Integer shopId);

    /**
     * 根据Shop修改一个店铺
     * @param shop
     */
    public int updateShop(Shop shop);

    /**
     * 带条件分页查询
     * @param shop
     * @return
     */
    QueryResult findByPage(Shop shop);

    /**
     * 添加一个商铺
     * @param shop
     * @return
     */
    public int addShop(Shop shop);

    /**
     * 传入一个商户id
     * @param userId
     * @return
     */
    List<ShopPO> findByShopId(Long userId);

}
