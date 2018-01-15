package com.dadao.refunds.activity;

import com.dadao.order.entity.OrderPO;
import com.dadao.order.mapper.OrderMapper;
import com.dadao.refunds.entity.RefundsApplicationPO;
import com.dadao.refunds.entity.RefundsRecord;
import com.dadao.refunds.mapper.IRefundsApplicationMapper;
import com.dadao.shop.entity.ShopPO;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by YunQiang on 2017/8/9
 */
@Repository
@Transactional
public class RefundsApplicationActivity {

    @Autowired
    private IRefundsApplicationMapper iRefundsApplicationMapper;

    @Autowired
    private OrderMapper orderMapper;

    public Result findRefundsByPage(ShopPO shopPO, Integer pageNum){
        if(shopPO.getShopId() == null){
            return new Result(ResultCode.ENTITY_ID_NULL);
        }
        shopPO.setBeginIndex(Long.valueOf((pageNum - 1) * shopPO.getPageSize()));
        List list = iRefundsApplicationMapper.findByPage(shopPO);
        for (Object object:list) {
            RefundsRecord refundsRecord = (RefundsRecord) object;
            //实际扣款金额：应扣金额 + 支付通道费用
            refundsRecord.setDeductReally(refundsRecord.getAmount().add(refundsRecord.getDeduct()));
        }
        return new Result(ResultCode.SYS_SUCCESS,list);
    }

    /**
     * 商家处理用户退款
     * @param input
     * @return
     */
    public Result updateRefundsById(RefundsApplicationPO input){
        //1)退款对象初始化
        RefundsApplicationPO refundsApplicationPO = new RefundsApplicationPO();
        OrderPO orderPO = new OrderPO();
        Boolean flag = false;
        if(input.getRefundsId() == null){
            return new Result(ResultCode.ENTITY_ID_NULL);
        }
        if(input.getStatus() == null || input.getStatus() != 1 && input.getStatus() != 4){
            return new Result(ResultCode.INPUT_PARAMS_FAIL);
        }
        //同意退款
        if(input.getStatus() == 1){
            refundsApplicationPO.setShopDescription("同意退款");
            refundsApplicationPO.setProcessDetails("商家审核通过");
            refundsApplicationPO.setStatus(1);
            refundsApplicationPO.setRefundsId(input.getRefundsId());
            iRefundsApplicationMapper.update(refundsApplicationPO);
            //将用户订单的状态修改为7,描述修改为退款中
            orderPO.setId(input.getOrderId());
            orderPO.setDescription("退款中");
            orderPO.setOrderStatus(7);
            orderMapper.update(orderPO);
        }
        //拒绝退款
        if(input.getStatus() == 4){
            refundsApplicationPO.setShopDescription("商家拒绝退款");
            refundsApplicationPO.setProcessDetails("商家审核未通过");
            refundsApplicationPO.setStatus(4);
            refundsApplicationPO.setRefundsId(input.getRefundsId());
            iRefundsApplicationMapper.update(refundsApplicationPO);
            //将用户订单的状态修改为8,描述修改为退款失败
            orderPO.setId(input.getOrderId());
            orderPO.setDescription("退款失败，商家拒绝退款");
            orderPO.setOrderStatus(8);
            orderMapper.update(orderPO);
        }
        return new Result(ResultCode.SYS_SUCCESS);
    }

}
