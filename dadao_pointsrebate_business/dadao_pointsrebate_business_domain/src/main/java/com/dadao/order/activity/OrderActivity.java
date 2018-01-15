package com.dadao.order.activity;

import com.dadao.order.entity.OrderPO;
import com.dadao.order.mapper.OrderMapper;
import com.dadao.utils.PageNew;
import com.dadao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by YunQiang on 2017/8/8
 */
@Repository
public class OrderActivity {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 商户查询所有退款订单
     * @param orderPO
     * @return
     */
    public List findRefundOrers(OrderPO orderPO, Integer pageNum){
        orderPO.setBeginIndex(Long.valueOf((pageNum - 1) * orderPO.getPageSize()));
        orderPO.setOrderStatus(6);
        return orderMapper.findByPage(orderPO);
    }

}
