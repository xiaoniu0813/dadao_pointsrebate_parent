package com.dadao.trade;

import com.dadao.common.BaseTest;
import com.dadao.order.entity.OrderPO;
import com.dadao.trade.mapper.RefundResultMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by YunQiang on 2017/11/24
 * @author YunQiang
 * @description 测试退款
 */
public class RefundTest extends BaseTest{

    @Autowired
    private RefundResultMapper refundResultMapper;

    private OrderPO orderPO;

    @Before
    public void init(){
        orderPO = new OrderPO();
        orderPO.setOrderId("G201707301645184");
        orderPO.setDescription("测试");
    }

    @Test
    @Transactional
    public void refundResult(){
        refundResultMapper.insertRefundResult(orderPO);
    }

    @Test
    public void selectByOrderId(){
        refundResultMapper.selectByOrderId("G201707301645184");
    }
}
