package com.dadao.shop;

import com.dadao.common.BaseTest;
import com.dadao.shop.entity.ShopGoodsPO;
import com.dadao.shop.mapper.ShopGoodsMapper;
import org.apache.ibatis.annotations.Param;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Transactional
public class ShopGoodsTest extends BaseTest{

    @Autowired
    private ShopGoodsMapper shopGoodsMapper;

    private ShopGoodsPO shopGoodsPO;

    @Before
    public void init(){
        shopGoodsPO = new ShopGoodsPO();
        shopGoodsPO.setId(1L);
        shopGoodsPO.setGoodsName("西游记");
        shopGoodsPO.setGoodsImg("www.baidu.com");
        shopGoodsPO.setGoodsPrice(new BigDecimal(15));
        shopGoodsPO.setDescription("中国四大名著");
        shopGoodsPO.setBrowseLinks("www.baidu.com");
        shopGoodsPO.setShopId(2L);
        shopGoodsPO.setUpdateTime(new Date());
        shopGoodsPO.setPayMode(1);
    }

    @Test
    public void add(){
        shopGoodsPO.setId(null);
        shopGoodsMapper.save(shopGoodsPO);
    }

    @Test
    public void update(){
        shopGoodsMapper.update(shopGoodsPO);
    }

    @Test
    public void findById(){
        shopGoodsMapper.findById(shopGoodsPO.getId());
    }

    @Test
    public void findByPage(){
        shopGoodsMapper.findByPage(shopGoodsPO);
        shopGoodsMapper.findCount(shopGoodsPO);
    }

    @Test
    public void goodsBelongToShop(){
        boolean flag = shopGoodsMapper.goodsBelongToShop(8L, "5d1442e93cbab8bb9b9d14fe3aa012d5");
        System.out.println(flag);
    }

}
