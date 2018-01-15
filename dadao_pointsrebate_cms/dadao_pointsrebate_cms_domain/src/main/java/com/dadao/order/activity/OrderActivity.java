package com.dadao.order.activity;

import com.dadao.order.entity.OrderPO;
import com.dadao.order.mapper.OrderMapper;
import com.dadao.refunds.entity.RefundsApplicationPO;
import com.dadao.refunds.mapper.RefundsApplicationMapper;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public class OrderActivity {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RefundsApplicationMapper refundsApplicationMapper;
    //查询订单表
    public List<OrderPO> findByRefund(OrderPO orderPO){
        return orderMapper.findByEntity(orderPO);
    }
    //更新退款状态
    @Transactional
    public int updateOrderStatus(OrderPO orderPO){
        RefundsApplicationPO refundsApplicationPO = new RefundsApplicationPO();
        refundsApplicationPO.setOrderId(orderPO.getId());
        refundsApplicationPO.setCreateTime(new Date());
        refundsApplicationPO.setStatus(orderPO.getRefundsStatus());

        refundsApplicationMapper.update(refundsApplicationPO);

        orderPO.setCreateTime(new Date());

        return orderMapper.update(orderPO);
    }

    /**
     * 商户所有订单信息
     * @param orderPO
     * @return
     */
    public List<OrderPO> findAllOrder(OrderPO orderPO){
        return orderMapper.findByEntity(orderPO);
    }

    /**
     * 分页查询订单表
     * @param orderPO
     * @return
     */
    public QueryResult findByPage(OrderPO orderPO){
        QueryResult queryResult = new QueryResult();
        //分页大小默认为10
        if(orderPO.getPageSize() == null){
            orderPO.setPageSize(10);
        }
        //默认第一页
        if(orderPO.getPageNum() == null){
            orderPO.setPageNum(1);
        }
        orderPO.setBeginIndex(Long.valueOf((orderPO.getPageNum() - 1) * orderPO.getPageSize()));
        //获取分页后的数据集合
        List<OrderPO> pageData = orderMapper.findByPage(orderPO);
        //数据集合
        queryResult.setList(pageData);
        //当前页
        queryResult.setPageNum(orderPO.getPageNum());
        //总记录数
        Long totalSize = orderMapper.findTotalSize(orderPO);
        queryResult.setTotalSize(totalSize);
        //总页数
        Long totalPage = totalSize % orderPO.getPageSize() == 0 ? (totalSize / orderPO.getPageSize()) : (totalSize / orderPO.getPageSize() + 1);
        queryResult.setTotalPage(totalPage);

        return queryResult;
    }

}
