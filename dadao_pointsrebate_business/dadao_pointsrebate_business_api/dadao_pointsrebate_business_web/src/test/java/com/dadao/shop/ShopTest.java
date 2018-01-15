package com.dadao.shop;

import com.dadao.common.BaseTest;
import com.dadao.shop.entity.Shop;
import com.dadao.shop.mapper.ShopMapper;
import com.dadao.shop.service.ShopService;
import com.dadao.utils.QueryResult;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class ShopTest extends BaseTest {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopMapper shopMapper;

    @Test
    public void testFindByPage() {
        Shop shop = new Shop();
        shop.setPageSize(1);
        QueryResult result = shopService.findByPage(shop);
        for (Object object : result.getList()) {
            System.out.println((Shop) object);
        }
    }

    @Test
    public void testFindById(){
        System.out.println(shopService.findById(2L));
    }

    @Test
    public void testUpdate(){
        Shop shop = new Shop();
        shop.setShopId(2L);
        shop.setShopName("a");
        shop.setPhone("a");
        System.out.println(shopService.update(shop));
    }

    @Test
    public void testFindAmount(){
        System.out.println(shopService.findAmount(1L));
    }

    @Test
    public void findShopIdByToken(){
        System.out.println(shopMapper.findShopIdByToken("5d1442e93cbab8bb9b9d14fe3aa012d5"));
    }

}
