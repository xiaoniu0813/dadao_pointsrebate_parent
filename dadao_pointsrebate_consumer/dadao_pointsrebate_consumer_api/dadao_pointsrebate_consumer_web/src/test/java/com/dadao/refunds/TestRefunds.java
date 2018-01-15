package com.dadao.refunds;

import com.dadao.common.BaseTest;
import com.dadao.refunds.entity.RefundsApplicationPO;
import com.dadao.refunds.mapper.IRefundsApplicationMapper;
import com.dadao.shop.entity.ShopPO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by YunQiang on 2017/8/9
 */
public class TestRefunds extends BaseTest{

    @Autowired
    private IRefundsApplicationMapper iRefundsApplicationMapper;

    private RefundsApplicationPO refundsApplicationPO;

    private ShopPO shopPO;

    @Before
    public void Init(){
        //1)退款对象初始化
        refundsApplicationPO = new RefundsApplicationPO();
        refundsApplicationPO.setCreateTime(new Date());
        refundsApplicationPO.setOrderId(6L);
        refundsApplicationPO.setUserId(70L);
        refundsApplicationPO.setUserDescription("产品有瑕疵，我要退款");
        refundsApplicationPO.setShopDescription("同意退款");
        refundsApplicationPO.setPlatformDescription("同意退款");
        refundsApplicationPO.setProcessDetails("处理中");
        refundsApplicationPO.setStatus(0);
        //2)店铺对象初始化
        shopPO = new ShopPO();
        shopPO.setShopId(6L);
    }

    @Test
    public void add(){
        iRefundsApplicationMapper.save(refundsApplicationPO);
    }

    @Test
    public void findByPage(){
        iRefundsApplicationMapper.findByPage(shopPO);
    }

}
