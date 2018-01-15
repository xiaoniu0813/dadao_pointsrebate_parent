package com.dadao.shop.service;

import com.dadao.shop.entity.ShopGoodsPO;
import com.dadao.utils.Result;

/**
 * @author YunQiang
 */
public interface ShopGoodsService {

    /**
     * 删除goods
     * @param id
     * @param token
     * @return
     */
    Result deleteGoods(long id, String token);

    /**
     * 删除goods
     * @param shopGoodsPO
     * @param token
     * @return
     */
    Result updateGoods(ShopGoodsPO shopGoodsPO,  String token);

    /**
     * 商品列表
     * @param token
     * @return
     */
    Result listGoods(String token, ShopGoodsPO shopGoodsPO, int pageNum);

    /**
     * 添加商品
     * @param shopGoodsPO
     * @param token
     * @return
     */
    Result addGoods(ShopGoodsPO shopGoodsPO, String token);

    /**
     * 通过id查找商品详情
     * @param shopGoodsPO
     * @return
     */
    Result findGoodsById(ShopGoodsPO shopGoodsPO);

}
