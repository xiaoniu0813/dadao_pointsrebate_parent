package com.dadao.shop.service;

import com.dadao.shop.entity.Shop;
import com.dadao.shop.vo.AmountVO;
import com.dadao.shop.vo.ShopVO;
import com.dadao.utils.QueryResult;

public interface ShopService {
    /**
     * @Author: yangrui
     * @Description: 分页
     * @Date: 下午6:42 2017/7/24
     */
    QueryResult findByPage(Shop shop);

    ShopVO findById(Long shopId);

    boolean update(Shop shop);

    AmountVO findAmount(Long fk_user_id);
}
