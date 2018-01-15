package com.dadao.refunds;

import com.dadao.common.BaseTest;
import com.dadao.refunds.entity.RefundsApplicationPO;
import com.dadao.refunds.mapper.IRefundsApplicationMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by YunQiang on 2017/8/10
 */
public class TestRefundsApplication extends BaseTest{

    @Autowired
    private IRefundsApplicationMapper iRefundsApplicationMapper;

    private RefundsApplicationPO refundsApplicationPO;

    @Before
    public void Init(){
        //1)退款对象初始化
        refundsApplicationPO = new RefundsApplicationPO();
        refundsApplicationPO.setShopDescription("同意退款");
        refundsApplicationPO.setProcessDetails("商家审核通过");
        refundsApplicationPO.setStatus(1);
        refundsApplicationPO.setRefundsId(8L);
    }

    @Test
    public void update(){
        System.out.println(refundsApplicationPO);
        iRefundsApplicationMapper.update(refundsApplicationPO);
    }

}
