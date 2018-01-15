package com.dadao.shop.service.impl;

import com.dadao.shop.activity.ShopActivity;
import com.dadao.shop.entity.Shop;
import com.dadao.shop.entity.ShopPO;
import com.dadao.shop.service.ShopService;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yunqiang1 on 2017/7/17.
 */
@Service
public class ShopServiceImpl implements ShopService{

    @Autowired
    private ShopActivity shopActivity;

    /**
     * 查询单个商铺信息
     * @param shopId
     * @return
     */
    public Shop findShopById(Integer shopId) {
        return shopActivity.findShopById(shopId);
    }

    /**
     * 修改商铺信息
     * @param shop
     * @return
     */
    public int updateShop(Shop shop) {
        return shopActivity.updateShop(shop);
    }

    /**
     * 查询所有商铺信息(分页查询)
     * @param shop
     * @return
     */
    public QueryResult findByPage(Shop shop){
        return shopActivity.findByPage(shop);
    }

    /**
     * 添加商铺信息
     * @param shop
     * @return
     */
    public int addShop(Shop shop) {
        return shopActivity.addShop(shop);
    }

    /**
     * 根据商户id查找所有子商户id
     * @param userId
     * @return
     */
    public List<ShopPO> findByShopId(Long userId) {
        return shopActivity.findByShopId(userId);
    }

}
