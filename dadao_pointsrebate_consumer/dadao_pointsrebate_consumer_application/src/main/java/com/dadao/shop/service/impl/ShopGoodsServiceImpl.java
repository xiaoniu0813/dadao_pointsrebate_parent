package com.dadao.shop.service.impl;

import com.dadao.activities.shop.activity.ShopGoodsActivity;
import com.dadao.shop.entity.ShopGoodsPO;
import com.dadao.shop.entity.ShopPO;
import com.dadao.shop.service.ShopGoodsService;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 2017/12/19
 */
@Service
public class ShopGoodsServiceImpl implements ShopGoodsService{

    @Autowired
    private ShopGoodsActivity shopGoodsActivity;
    @Override
    public ShopPO showShopGoods(ShopGoodsPO shopGoodsPO) {
        return shopGoodsActivity.showShopGoods(shopGoodsPO);
    }

    @Override
    public ShopGoodsPO findGoodsById(Long id) {
        return shopGoodsActivity.findGoodsById(id);
    }

    @Override
    public QueryResult queryGoods(Integer payMode, String token, Integer status, Integer orderStatus, Integer pageNum, Integer pageSize) {
        return shopGoodsActivity.queryGoods(payMode,token,status,orderStatus,pageNum,pageSize);
    }

    @Override
    public ShopGoodsPO findGoodsByObject(ShopGoodsPO shopGoodsPO) {
        return shopGoodsActivity.findGoodsByObject(shopGoodsPO);
    }
}
