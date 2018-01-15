package com.dadao.activities.order.activity;

import com.dadao.order.entity.ConsumptionRecording;
import com.dadao.order.entity.OrderPO;
import com.dadao.order.mapper.IOrderMapper;
import com.dadao.user.entity.UserAccount;
import com.dadao.user.mapper.UserMapper;
import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * 处理订单表业务逻辑
 *
 * @auther NFY niufuyang
 * @create 2017-08-01
 */
@Repository
public class OrderActivity {

    @Autowired
    private IOrderMapper iOrderMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询用户消费列表信息  --niufy
     * @param orderPO
     * @param token
     * @return
     */
    public QueryResult findByPage(OrderPO orderPO,String token){
        UserAccount account=new UserAccount();
        account.setToken(token);
        account=userMapper.findByToken(account);
        if (account==null){
            return null;
        }
        orderPO.setUserId(account.getUserId());
        orderPO.setOrderStatus(0);
        QueryResult queryResult = new QueryResult();
        Long totalSize = iOrderMapper.findCount(orderPO);
        List list = iOrderMapper.findByPage(orderPO);
        Long totalPage = totalSize % orderPO.getPageSize() == 0 ? totalSize / orderPO.getPageSize() : totalSize / orderPO.getPageSize() + 1;
        queryResult.setTotalPage(totalPage);
        queryResult.setTotalSize(totalSize);
        queryResult.setList(list);
        return queryResult;
    }

    /**
     * 查询用户消费详情  --niufy
     * @param consumptionRecording
     * @param token
     * @return
     */
    public ConsumptionRecording findConsumptionRecordingInfo(ConsumptionRecording consumptionRecording,String token){
        UserAccount account=new UserAccount();
        account.setToken(token);
        account=userMapper.findByToken(account);
        if(account==null){
            return null;
        }
        consumptionRecording.setUserId(account.getUserId());
        return (ConsumptionRecording) iOrderMapper.findConsumptionRecordingInfo(consumptionRecording);
    }

    /**
     * 查询商户交易
     * @param orderPO
     * @return
     */
    public List<OrderPO> findShopDeal(OrderPO orderPO){
        return iOrderMapper.findByEntity(orderPO);
    }

}
