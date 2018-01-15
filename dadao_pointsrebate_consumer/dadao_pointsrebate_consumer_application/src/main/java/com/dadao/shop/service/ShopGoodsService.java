package com.dadao.shop.service;

import com.dadao.shop.entity.ShopGoodsPO;
import com.dadao.shop.entity.ShopPO;
import com.dadao.utils.QueryResult;

import java.util.List;

/**
 * @Author GUOYU 2017/12/19
 */
public interface ShopGoodsService {

    /**
     * 查询商铺下的所有信息
     * @param shopGoodsPO
     * @return
     */
    ShopPO showShopGoods(ShopGoodsPO shopGoodsPO);

    /**
     * 查询单个商品详细信息
     * @param id
     * @return
     */
    ShopGoodsPO findGoodsById(Long id);

    /**
     *
     * 根据条件查询商品列表
     * @param payMode
     * @param token
     * @param status
     * @param orderStatus
     * @param pageNum
     * @param pageSize
     * @return
     */
    QueryResult queryGoods(Integer payMode, String token, Integer status, Integer orderStatus, Integer pageNum, Integer pageSize);

    /**
     * 查询单条消费记录
     * @param shopGoodsPO
     * @return
     */
    ShopGoodsPO findGoodsByObject(ShopGoodsPO shopGoodsPO);
}
