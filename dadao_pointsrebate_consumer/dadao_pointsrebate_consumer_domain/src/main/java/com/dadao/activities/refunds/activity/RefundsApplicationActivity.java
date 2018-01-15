package com.dadao.activities.refunds.activity;

import com.dadao.order.entity.OrderPO;
import com.dadao.order.mapper.IOrderMapper;
import com.dadao.push.entity.InformationPO;
import com.dadao.push.service.IPushService;
import com.dadao.refunds.entity.RefundsApplicationPO;
import com.dadao.refunds.mapper.IRefundsApplicationMapper;
import com.dadao.shop.entity.Shop;
import com.dadao.shop.mapper.IShopMapper;
import com.dadao.user.entity.UserAccount;
import com.dadao.user.mapper.UserMapper;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by YunQiang on 2017/8/9
 */
@Repository
@Transactional
public class RefundsApplicationActivity {

    @Autowired
    private IRefundsApplicationMapper iRefundsApplicationMapper;

    @Autowired
    private IOrderMapper iOrderMapper;

    @Autowired
    private IPushService iPushService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IShopMapper iShopMapper;

    /**
     * 用户申请退款
     * @param orderId
     * @return
     */
    public Result saveRefunds(Long orderId, String token, String reason){
        //(1)找到订单
        OrderPO orderPO = (OrderPO) iOrderMapper.findById(orderId);
        if(orderPO == null){
            return new Result(ResultCode.ENTITY_ID_FAIL,orderPO);
        }
        //(2)修改订单状态为6,如果当前订单状态不为5则订单不可退款
        if(orderPO.getOrderStatus() != 5){
            return new Result(ResultCode.ORDER_REFUND_FAIL,orderPO);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("token", token);
        hashMap.put("updateTime",new Date());
        hashMap.put("orderStatus",6);
        hashMap.put("id",orderPO.getId());
        Boolean flag = iOrderMapper.update(hashMap) == 1;

        if(!flag){
            return new Result(ResultCode.ORDER_REFUND_FAIL,orderPO);
        }
        //(3)在退款表中插入一条记录
        RefundsApplicationPO refundsApplicationPO = new RefundsApplicationPO();
        refundsApplicationPO.setCreateTime(new Date());
        refundsApplicationPO.setOrderId(orderId);
        refundsApplicationPO.setUserId(orderPO.getUserId());
        refundsApplicationPO.setStatus(0);
        refundsApplicationPO.setProcessDetails("处理中");
        refundsApplicationPO.setUserDescription(reason);
        iRefundsApplicationMapper.save(refundsApplicationPO);

        //找到申请退款用户、处理退款商户
        UserAccount userAccount = (UserAccount)userMapper.findById(orderPO.getUserId());
        Shop shopPO = (Shop) iShopMapper.findById(orderPO.getShopId());

        //向商户推送一条退款申请消息
        InformationPO informationPO = new InformationPO();
        informationPO.setTitle("用户申请退款");
        informationPO.setContent("用户" + userAccount.getPhone() + "申请" + orderPO.getAmount() + "元退款,请您及时处理！");
        informationPO.setUserId(shopPO.getFk_user_id());
        informationPO.setHaveRead(0);
        informationPO.setStatus(2);
        informationPO.setObjectId(orderPO.getId());

        iPushService.pushForBusiness("收到用户退款申请，请您及时处理！", informationPO);

        return new Result(ResultCode.SYS_SUCCESS);
    }



}
