package com.dadao.shop.service.impl;

import com.dadao.shop.activity.ShopActivity;
import com.dadao.shop.entity.Shop;
import com.dadao.shop.service.ShopService;
import com.dadao.shop.vo.AmountVO;
import com.dadao.shop.vo.ShopVO;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopActivity shopActivity;

    @Override
    public QueryResult findByPage(Shop shop) {
        return shopActivity.listFindByPage(shop);
    }

    @Override
    public ShopVO findById(Long shopId) {
        return shopActivity.findById(shopId);
    }

    @Override
    public boolean update(Shop shop) {
        return shopActivity.update(shop);
    }

    @Override
    public AmountVO findAmount(Long fk_user_id) {
        return shopActivity.findAmount(fk_user_id);
    }
}
