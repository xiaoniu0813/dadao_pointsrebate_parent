package com.dadao.shop.service;

import com.dadao.shop.entity.RecommendPosition;
import com.dadao.shop.entity.Shop;
import com.dadao.shop.entity.ShopPO;
import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;

import java.util.List;

/**
 * Created by yangrui on 2017/7/16.
 */
public interface ShopService {

    QueryResult findByPage(RecommendPosition recommendPosition);

    /**
     * 获取附近商铺
     * @param shop
     * @return
     */
    QueryResult getNearShops(Shop shop);

    Result findShopById(Integer shopId);

    QueryResult listRecommendShop(Shop shop);

    /**
     * 智能搜索
     * @param shopPO
     * @param pageNum
     * @param type
     * @return
     */
    Result getShopByConditions(ShopPO shopPO,Integer pageNum,Integer type);

    /**
     * 用户端启动页推荐商铺
     * @return
     */
    Result findStartupShop();

    /**
     * 用户端首页3块展示区
     * @param longitude
     * @param latitude
     * @param categoryId
     * @param rankType
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result findShopsByRankType(String longitude, String latitude, Long categoryId, Integer rankType, Integer pageNum, Integer pageSize);

    /**
     * 查询商户所有店铺
     * @param shopPO
     * @return
     */
    List<ShopPO> list(ShopPO shopPO);
}
