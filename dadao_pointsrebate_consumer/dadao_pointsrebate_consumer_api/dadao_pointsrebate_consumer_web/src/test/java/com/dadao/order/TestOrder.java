package com.dadao.order;

import com.dadao.common.BaseTest;
import com.dadao.order.entity.OrderPO;
import com.dadao.order.mapper.IOrderMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by YunQiang on 2017/8/7
 */
public class TestOrder extends BaseTest{

    @Autowired
    private IOrderMapper iOrderMapper;

    private OrderPO orderPO;

    @Before
    public void Init(){
        orderPO = new OrderPO();
        orderPO.setId(10L);
    }

    @Test
    /**
     * 退款
     */
    public void test1(){
        orderPO.setOrderStatus(6);
        orderPO.setDescription("货物质量有问题!");
        int flag = iOrderMapper.update(orderPO);
        System.out.println("退款申请" + flag);
    }

    @Test
    /**
     * 取消退款
     */
    public void test2(){
        orderPO.setOrderStatus(4);
        orderPO.setDescription("货物质量没问题!");
        int flag = iOrderMapper.update(orderPO);
        System.out.println("取消退款" + flag);
    }

}
