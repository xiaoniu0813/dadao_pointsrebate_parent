package com.dadao.shop;

import com.dadao.common.BaseTest;
import com.dadao.shop.entity.ShopPO;
import com.dadao.shop.mapper.IShopMapper;
import com.dadao.utils.DaDaoUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by YunQiang on 2017/8/4
 */
public class TestShop extends BaseTest{

    private ShopPO shopPO;


    @Autowired
    private IShopMapper iShopMapper;

    private HashMap hashMap;

    @Before
    public void Init(){
        shopPO = new ShopPO();
        shopPO.setCategoryId(21);
        shopPO.setLatitude("123.4");
        shopPO.setLongitude("53.6");
        shopPO.setScope(5000);
        shopPO.setPerCapitaConsumption(2);
        shopPO.setIntegralRate(1);
        shopPO.setTags("停车，WI-FI");
        //组装查询条件
        hashMap = DaDaoUtil.objToHash(shopPO);

        String[] targs = shopPO.getTags().split(",|，");
        System.out.println(targs.length);
        System.out.println(Arrays.toString(targs));

        //1.拆分tags
        hashMap.put("tags",targs);
    }

  @Test
    public void testGetShopById(){
        iShopMapper.getById(8);
    }

    @Test
    public void listNearByShop(){
        iShopMapper.listNearByShop(hashMap);
    }

    @Test
    public void countNearByShop(){
        Long l = iShopMapper.countNearByShop(hashMap);
    }
    @Test
    public void test(){
        String s = "店铺, 大牛, 小牛";
        System.out.println(Arrays.toString(s.split(",")));
    }

    @Test
    public void findShopsByRankType(){
        iShopMapper.findShopsByRankType("116.32403999999998","40.04751350579131",1L,2,0,5);
        iShopMapper.countShopsByRankType("116.32403999999998","40.04751350579131",1L,2);
    }


}
