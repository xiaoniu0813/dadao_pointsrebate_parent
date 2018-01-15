package com.dadao.activities.pay.activity;

import com.dadao.capitalpool.entity.CapitalpoolPO;
import com.dadao.capitalpool.entity.CapitalpoolRecordPO;
import com.dadao.capitalpool.entity.CapitalpoolVO;
import com.dadao.capitalpool.mapper.CapitalpoolMapper;
import com.dadao.capitalpool.mapper.CapitalpoolRecordMapper;
import com.dadao.order.entity.OrderPO;
import com.dadao.order.mapper.IOrderMapper;
import com.dadao.pay.entity.PaymentDetails;
import com.dadao.shop.entity.vo.Shop;
import com.dadao.shop.mapper.IShopMapper;
import com.dadao.user.entity.*;
import com.dadao.user.mapper.IUserIntegralMapper;
import com.dadao.user.mapper.IUserIntegralRecordingMapper;
import com.dadao.user.mapper.IUserTransactionRecodMapper;
import com.dadao.user.mapper.UserWalletMapper;
import com.dadao.user.mapper.entity.UserIntegral;
import com.dadao.utils.ArithUtil;
import com.dadao.utils.DateUtils;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;

/**
 * 处理支付回调（分账、给用户积分）
 *
 * @auther NFY niufuyang
 * @create 2017-10-10
 */
@Repository
public class PayTheCallbackActivity {

    @Autowired
    private IShopMapper iShopMapper;
    @Autowired
    private IUserIntegralMapper userIntegralMapper;
    @Autowired
    private IOrderMapper iOrderMapper;
    @Autowired
    private IUserIntegralRecordingMapper iUserIntegralRecordingMapper;
    @Autowired
    private IUserTransactionRecodMapper userTransactionRecodMapper;
    @Autowired
    private UserWalletMapper userWalletMapper;
    @Autowired
    private CapitalpoolMapper capitalpoolMapper;
    @Autowired
    private CapitalpoolRecordMapper capitalpoolRecordMapper;

    /**
     * 处理支付回调  ---niufy
     *
     * @param paymentDetails
     * @return
     */
    @Transactional
    public Result PayTheCallback(PaymentDetails paymentDetails) {
        if (paymentDetails != null) {
            Shop shop = (Shop) iShopMapper.getById(paymentDetails.getShopId().intValue());
            if (shop != null) {
                //生成订单
                OrderPO orderPO = new OrderPO();
                orderPO.setShopId(paymentDetails.getShopId());
                orderPO.setUserId(paymentDetails.getUserId());
                orderPO.setOrderId(DateUtils.getTime());
                orderPO.setAmount(paymentDetails.getTransactionAmount());
                orderPO.setMarketId(21);
                orderPO.setPayMethod(paymentDetails.getPayManner());
                //判断是否支付成功
                if (paymentDetails.getPayStatus()) {
                    orderPO.setOrderStatus(5);
                } else {
                    return new Result(ResultCode.PAYTHECALLBACK_PAY_FAIL);
                }
                orderPO.setChannelSequence(paymentDetails.getChannelSequence());
                //保存订单
                iOrderMapper.save(orderPO);
                //计算用户所得积分
                BigDecimal integral = ArithUtil.div(ArithUtil.mul(paymentDetails.getTransactionAmount(), new BigDecimal(shop.getIntegralRate())), new BigDecimal(100), 2);
                UserIntegralVO userIntegralVO = userIntegralMapper.findByUserID(paymentDetails.getUserId());
                //userIntegralVO.setIntegral(userIntegralVO.getIntegral()+integral);
                UserIntegral userIntegral = new UserIntegral();
                try {
                    BeanUtils.copyProperties(userIntegral, userIntegralVO);
                } catch (Exception e) {
                    e.printStackTrace();
                    //设置手动回滚
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                }
                userIntegral.setIntegral(ArithUtil.add(userIntegral.getIntegral(), integral));
                //更新用户积分
                userIntegralMapper.update(userIntegral);
                //更新用户积分记录
                UserIntegralRecordingPO userIntegralRecordingPO = new UserIntegralRecordingPO();
                userIntegralRecordingPO.setUserId(paymentDetails.getUserId());
                userIntegralRecordingPO.setDirection(0);
                userIntegralRecordingPO.setMarketId(21L);
                userIntegralRecordingPO.setObjectId(orderPO.getId());
                userIntegralRecordingPO.setIntegral(integral);
                //保存用户积分记录
                iUserIntegralRecordingMapper.save(userIntegralRecordingPO);
                //生成用户交易记录
                UserTransactionRecodPO userTransactionRecodPO = new UserTransactionRecodPO();
                userTransactionRecodPO.setOtherAccount(shop.getFk_user_id().toString());
                userTransactionRecodPO.setTransactionType(2);
                userTransactionRecodPO.setTransactionAmount(paymentDetails.getTransactionAmount());
                userTransactionRecodPO.setExpenditureIncome(0);
                userTransactionRecodPO.setUserId(paymentDetails.getUserId());
                userTransactionRecodPO.setPayMethod(paymentDetails.getPayManner());
                //计算平台佣金
                BigDecimal commission = ArithUtil.div(ArithUtil.mul(paymentDetails.getTransactionAmount(), new BigDecimal(shop.getCommissionRate())), new BigDecimal(100), 2);
                userTransactionRecodPO.setCommission(commission);
                userTransactionRecodPO.setSerialNumber(paymentDetails.getChannelSequence());
                //保存用户交易明细
                userTransactionRecodMapper.save(userTransactionRecodPO);
                //生成商户交易记录
                userTransactionRecodPO.setTransactionType(1);
                userTransactionRecodPO.setExpenditureIncome(1);
                userTransactionRecodPO.setOtherAccount(paymentDetails.getUserId().toString());
                userTransactionRecodPO.setUserId(shop.getFk_user_id());
                userTransactionRecodPO.setActualIncome(ArithUtil.sub(paymentDetails.getTransactionAmount(),commission));
                //保存商户交易明细
                userTransactionRecodMapper.save(userTransactionRecodPO);
                //更新商户钱包
                UserWalletVO userWalletVO=(UserWalletVO)userWalletMapper.findById(shop.getFk_user_id());
                UserWalletPO userWalletPO=new UserWalletPO();
                try {
                    BeanUtils.copyProperties(userWalletPO, userWalletVO);
                } catch (Exception e) {
                    e.printStackTrace();
                    //设置手动回滚
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                }
                userWalletPO.setBalance(ArithUtil.add(userWalletPO.getBalance(),userTransactionRecodPO.getActualIncome()));
                //更新商户钱包
                userWalletMapper.update(userWalletPO);
                //更新资金池
                CapitalpoolVO capitalpoolVO=capitalpoolMapper.findBymMarketId(21L);
                CapitalpoolPO capitalpoolPO=new CapitalpoolPO();
                try {
                    BeanUtils.copyProperties(capitalpoolPO, capitalpoolVO);
                } catch (Exception e) {
                    e.printStackTrace();
                    //设置手动回滚
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                }
                //计算返佣金额
                BigDecimal CommissionMoney=ArithUtil.div(ArithUtil.mul(paymentDetails.getTransactionAmount(),new BigDecimal(shop.getCommissionRate())),new BigDecimal(100),2);
                capitalpoolPO.setMoney(ArithUtil.add(capitalpoolPO.getMoney(),CommissionMoney));
                capitalpoolMapper.update(capitalpoolPO);
                //生成资金池记录
                CapitalpoolRecordPO capitalpoolRecordPO=new CapitalpoolRecordPO();
                capitalpoolRecordPO.setObjectId(orderPO.getId());
                capitalpoolRecordPO.setStatus(2);
                capitalpoolRecordPO.setMoney(CommissionMoney);
                capitalpoolRecordPO.setMarketId(21L);
                capitalpoolRecordPO.setDescription("积分所得");
                //保存资金池记录
                capitalpoolRecordMapper.save(capitalpoolRecordPO);
            } else {
                return new Result(ResultCode.PAYTHECALLBACK_SHOPID_FAIL);
            }
        } else {
            return new Result(ResultCode.PAYTHECALLBACK_PRM_FAIL);
        }
        return new Result(ResultCode.SYS_SUCCESS);
    }
}
