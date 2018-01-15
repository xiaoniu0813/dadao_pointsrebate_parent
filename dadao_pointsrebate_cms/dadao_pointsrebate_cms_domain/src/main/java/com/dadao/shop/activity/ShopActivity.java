package com.dadao.shop.activity;

import com.dadao.shop.entity.Shop;
import com.dadao.shop.entity.ShopPO;
import com.dadao.shop.mapper.ShopMapper;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yunqiang1 on 2017/7/17.
 */
@Repository
public class ShopActivity {

    @Autowired
    private ShopMapper shopMapper;


    public Shop findShopById(Integer shopId){
        return (Shop) shopMapper.findById(shopId);
    }

    /**
     * 根据Shop修改一个店铺
     * @param shop
     */
    public Integer updateShop(Shop shop){
        return shopMapper.update(shop);
    }

    /**
     * 查询商铺分页
     * @param niufy
     */
    public QueryResult findByPage(Shop shop){
        QueryResult queryResult = new QueryResult();
        Long totalSize = shopMapper.findCount(shop);
        List list = shopMapper.findByPage(shop);
        Long totalPage = totalSize % shop.getPageSize() == 0 ? totalSize / shop.getPageSize() : totalSize / shop.getPageSize() + 1;
        queryResult.setTotalPage(totalPage);
        queryResult.setTotalSize(totalSize);
        queryResult.setList(list);
        return queryResult;
    }

    /**
     * 添加商铺
     * @param shop
     * @return
     */
    public int addShop(Shop shop){
        int a = shopMapper.save(shop);
        return a;
    }

    /**
     * 传入一个商户id
     * @param userId
     * @return
     */
    public List<ShopPO> findByShopId(Long userId){
        return shopMapper.findByShopId(userId);
    }
}

