package com.dadao.callback.service.impl;

import com.dadao.callback.service.IPayCallbackService;
import com.dadao.capitalpool.entity.CapitalpoolPO;
import com.dadao.capitalpool.entity.CapitalpoolRecordPO;
import com.dadao.capitalpool.mapper.CapitalpoolMapper;
import com.dadao.capitalpool.mapper.ICapitalpoolRecordMapper;
import com.dadao.merchants.entity.MerchantsDivided;
import com.dadao.merchants.mapper.IMerchantsDividedMapper;
import com.dadao.order.entity.OrderPO;
import com.dadao.order.entity.OrderVO;
import com.dadao.order.entity.YOPCallbackParameter;
import com.dadao.order.mapper.IOrderMapper;
import com.dadao.pub.utils.YOPTODADAO;
import com.dadao.push.entity.InformationPO;
import com.dadao.push.service.IPushService;
import com.dadao.shop.entity.Shop;
import com.dadao.shop.mapper.IShopMapper;
import com.dadao.user.entity.UserIntegral;
import com.dadao.user.entity.UserIntegralRecordingPO;
import com.dadao.user.entity.UserTransactionRecodPO;
import com.dadao.user.mapper.IUserIntegralMapper;
import com.dadao.user.mapper.IUserIntegralRecordingMapper;
import com.dadao.user.mapper.IUserTransactionRecodMapper;
import com.dadao.utils.ArithUtil;
import com.dadao.utils.DaDaoUtil;
import com.dadao.yop.service.YeepayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付回调实现类
 *
 * @auther NFY niufuyang
 * @create 2017-11-20
 */
@Service
public class IPayCallbackServiceImpl implements IPayCallbackService {

    @Autowired
    private IOrderMapper iOrderMapper;
    @Autowired
    private IShopMapper iShopMapper;
    @Autowired
    private IUserIntegralMapper iUserIntegralMapper;
    @Autowired
    private IUserIntegralRecordingMapper iUserIntegralRecordingMapper;
    @Autowired
    private IUserTransactionRecodMapper iUserTransactionRecodMapper;
    @Autowired
    private CapitalpoolMapper capitalpoolMapper;
    @Autowired
    private ICapitalpoolRecordMapper capitalpoolRecordMapper;
    @Autowired
    private IPushService iPushService;
    @Autowired
    private IMerchantsDividedMapper iMerchantsDividedMapper;

    /**
     * 易宝支付回调
     *
     * @param
     */
    @Transactional
    public String YOPPayCallback(String response) throws Exception {
        YOPCallbackParameter yopCallbackParameter = new YOPCallbackParameter();
        //解密参数
        Map<String, String> result = YeepayService.callback(response);
        String parentMerchantNo = result.get("parentMerchantNo");
        String merchantNo = result.get("merchantNo");
        String orderId = result.get("orderId");
        String uniqueOrderNo = result.get("uniqueOrderNo");
        String status = result.get("status");
        String orderAmount = result.get("orderAmount");
        String payAmount = result.get("payAmount");
        String requestDate = result.get("requestDate");
        String paySuccessDate = result.get("paySuccessDate");
        yopCallbackParameter.setParentMerchantNo(parentMerchantNo);
        yopCallbackParameter.setMerchantNo(merchantNo);
        yopCallbackParameter.setOrderId(orderId);
        yopCallbackParameter.setUniqueOrderNo(uniqueOrderNo);
        yopCallbackParameter.setStatus(status);
        yopCallbackParameter.setOrderAmount(orderAmount);
        yopCallbackParameter.setPayAmount(payAmount);
        yopCallbackParameter.setRequestDate(requestDate);
        yopCallbackParameter.setPaySuccessDate(paySuccessDate);

        //查询订单信息
        OrderVO orderVO = iOrderMapper.findByCchannelSequence(yopCallbackParameter.getUniqueOrderNo());
        if (orderVO.getOrderStatus() != null && orderVO.getOrderStatus() != 5) {
            //设置时间格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //修改订单支付状态
            OrderPO orderPO = new OrderPO();
            orderPO.setChannelSequence(yopCallbackParameter.getUniqueOrderNo());
            orderPO.setOrderStatus(5);
            orderPO.setUpdateTime(sdf.parse(sdf.format(new Date())));
            orderPO.setPayTime(sdf.parse(yopCallbackParameter.getPaySuccessDate()));
            System.out.println("orderPO:" + orderPO);
            int updateOrderStatusResult = iOrderMapper.update(orderPO);
            if (updateOrderStatusResult != 1) {
                throw new Exception("支付回调时修改订单状态失败！");
            }

            //查询收款商户信息
            Shop shop = iShopMapper.findByShopId(orderVO.getShopId());

            //计算用户所得积分
            BigDecimal integral = ArithUtil.divDown(ArithUtil.mul(orderVO.getAmount(), new BigDecimal(shop.getIntegralRate())), new BigDecimal(100), 2);

            //查询付款用户积分信息
            UserIntegral userIntegral = new UserIntegral();
            userIntegral.setUserId(orderVO.getUserId());
            userIntegral.setMarketId(orderVO.getMarketId().longValue());
            userIntegral = iUserIntegralMapper.findByUserId(userIntegral);

            //更新用户积分
            userIntegral.setIntegral(ArithUtil.add(userIntegral.getIntegral(), integral));
            int updateUserIntegralResult = iUserIntegralMapper.update(userIntegral);
            if (updateUserIntegralResult != 1) {
                throw new Exception("支付回调时更新用户积分失败！");
            }

            //保存用户积分记录
            UserIntegralRecordingPO userIntegralRecordingPO = new UserIntegralRecordingPO();
            userIntegralRecordingPO.setUserId(userIntegral.getUserId());
            userIntegralRecordingPO.setDirection(0);
            userIntegralRecordingPO.setMarketId(orderVO.getMarketId().longValue());
            userIntegralRecordingPO.setObjectId(orderVO.getId());
            userIntegralRecordingPO.setIntegral(integral);
            userIntegralRecordingPO.setDescription("向 " + shop.getShopName() + " 付款获得 " + integral + " 积分");
            userIntegralRecordingPO.setStatus(0);
            int saveUserIntegralResult = iUserIntegralRecordingMapper.save(userIntegralRecordingPO);
            if (saveUserIntegralResult != 1) {
                throw new Exception("支付回调时保存用户积分记录失败！");
            }

            //生成用户交易记录
            UserTransactionRecodPO userTransactionRecodPO = new UserTransactionRecodPO();
            userTransactionRecodPO.setOtherAccount(shop.getFk_user_id().toString());
            userTransactionRecodPO.setTransactionType(4);
            userTransactionRecodPO.setTransactionAmount(orderVO.getAmount());
            userTransactionRecodPO.setExpenditureIncome(0);
            userTransactionRecodPO.setUserId(orderVO.getUserId());
            userTransactionRecodPO.setTransactionDetails("向 " + shop.getShopName() + " 付款 " + orderVO.getAmount() + " 元");
            userTransactionRecodPO.setPayMethod(orderVO.getPayMethod());
            //计算支付通道费用
            BigDecimal payCanalFee = new BigDecimal(0);
            if (orderVO.getPayMethod() == 1 || orderVO.getPayMethod() == 2) {
                payCanalFee = ArithUtil.mul(orderVO.getAmount(), new BigDecimal(0.009));
            } else if (orderVO.getPayMethod() == 3) {
                payCanalFee = ArithUtil.mul(orderVO.getAmount(), new BigDecimal(0.0045));
            }
            userTransactionRecodPO.setPayCanalFee(payCanalFee);
            userTransactionRecodPO.setSerialNumber(orderVO.getChannelSequence());
            //计算平台佣金
            BigDecimal commission = ArithUtil.div(ArithUtil.mul(orderVO.getAmount(), new BigDecimal(shop.getCommissionRate())), new BigDecimal(100), 2);
            userTransactionRecodPO.setCommission(commission);
            //保存用户交易记录
            int saveUserTransactionRecordResult = iUserTransactionRecodMapper.save(userTransactionRecodPO);
            Long consumerResult = userTransactionRecodPO.getTransactionId();
            if (saveUserTransactionRecordResult != 1) {
                throw new Exception("支付回调时保存用户交易记录失败！");
            }

            //生成商户交易记录
            userTransactionRecodPO.setTransactionType(1);
            userTransactionRecodPO.setExpenditureIncome(1);
            userTransactionRecodPO.setOtherAccount(orderVO.getUserId().toString());
            userTransactionRecodPO.setUserId(shop.getFk_user_id());
            //计算商户应得金额
            BigDecimal actualIncome = ArithUtil.sub(orderVO.getAmount(), ArithUtil.add(commission, payCanalFee));
            userTransactionRecodPO.setActualIncome(actualIncome);
            userTransactionRecodPO.setTransactionDetails("收到用户付款 " + orderVO.getAmount() + " 元");
            //保存商户交易记录
            int saveMerchantTransactionRecodResult = iUserTransactionRecodMapper.save(userTransactionRecodPO);
            Long businessResult = userTransactionRecodPO.getTransactionId();
            if (saveMerchantTransactionRecodResult != 1) {
                throw new Exception("支付回调时保存商户交易记录失败！");
            }

            //分账开始

            //格式化时间精确到毫秒
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            //拼接分账请求号
            String divideRequestId = "DADAO_" + shop.getShopId() + formatter.format(new Date());
            String contractNo = shop.getShopId()+"_"+shop.getSubMerchantNo();
            String ledgerNo = shop.getSubMerchantNo();
            String ledgerName = shop.getShopName();
            String amount = actualIncome+"";
            String divideDetail = "[{\"ledgerNo\":\"" + ledgerNo + "\",\"ledgerName\":\"" + ledgerName + "\",\"amount\":\"" + amount + "\"}]";

            //拼接调用分账接口
            Map<String, String> params = new HashMap();
            params.put("parentMerchantNo", parentMerchantNo);
            params.put("merchantNo", YeepayService.getReceiptMerchantNo());
            params.put("divideRequestId", divideRequestId);
            params.put("orderId", orderId);
            params.put("uniqueOrderNo", uniqueOrderNo);
            params.put("contractNo", contractNo);
            params.put("divideDetail", divideDetail);
            String uri = YeepayService.getUrl("tradedivideURI");
            Map<String,String> traderesult= YOPTODADAO.requestYOP(params,uri,YeepayService.TRADEDIVIDE,YeepayService.DIVIDED_HMAC);
            System.out.println("回调后分账："+traderesult.get("code")+"<--->"+traderesult.get("message"));

            //保存分账信息
            MerchantsDivided merchantsDivided=new MerchantsDivided();
            merchantsDivided.setDivideRequestId(traderesult.get("parentMerchantNo"));
            merchantsDivided.setOrderId(traderesult.get("orderId"));
            merchantsDivided.setUniqueOrderNo(traderesult.get("uniqueOrderNo"));
            merchantsDivided.setContractNo(contractNo);
            merchantsDivided.setLedgerNo(ledgerNo);
            merchantsDivided.setLedgerName(ledgerName);
            merchantsDivided.setAmount(actualIncome);
            merchantsDivided.setDivideDetail(traderesult.get("divideDetail"));
            merchantsDivided.setStatus(traderesult.get("status"));
            merchantsDivided.setMessage(traderesult.get("code")+"："+traderesult.get("message"));
            int saveMerchantsDividedResult=iMerchantsDividedMapper.save(merchantsDivided);
            if (saveMerchantsDividedResult != 1) {
                throw new Exception("支付回调时保存分账信息失败！");
            }

            //分账结束

            //更新资金池
            CapitalpoolPO capitalpoolPO = capitalpoolMapper.findBymMarketId(orderVO.getMarketId().longValue());
            //计算返佣金额
            BigDecimal CommissionMoney = ArithUtil.sub(commission, payCanalFee);
            capitalpoolPO.setMoney(ArithUtil.add(capitalpoolPO.getMoney(), CommissionMoney));
            int updateCapitalpoolResult = capitalpoolMapper.update(capitalpoolPO);
            if (updateCapitalpoolResult != 1) {
                throw new Exception("支付回调时更新资金池失败！");
            }

            //生成资金池记录
            CapitalpoolRecordPO capitalpoolRecordPO = new CapitalpoolRecordPO();
            capitalpoolRecordPO.setObjectId(orderVO.getId());
            capitalpoolRecordPO.setStatus(0);
            capitalpoolRecordPO.setMoney(CommissionMoney);
            capitalpoolRecordPO.setMarketId(21L);
            capitalpoolRecordPO.setDescription("消费返佣");
            int saveCapitalpoolRecordResult = capitalpoolRecordMapper.save(capitalpoolRecordPO);
            if (saveCapitalpoolRecordResult != 1) {
                throw new Exception("支付回调时保存资金池记录失败！");
            }



            //推送消息实体(商户)
            InformationPO informationPO = new InformationPO();
            informationPO.setTitle("收款消息");
            informationPO.setContent("收到用户付款 " + orderVO.getAmount() + " 元");
            informationPO.setUserId(shop.getFk_user_id());
            informationPO.setStatus(1);
            informationPO.setObjectId(businessResult);

            //向商户推送支付消息
            //DaDaoUtil.pushForBusiness(shop.getFk_user_id().toString(), "收到用户付款 " + orderVO.getAmount() + " 元");
            iPushService.pushForBusiness(informationPO.getContent(), informationPO);
            //推送消息实体(用户)
            informationPO.setContent("在线支付 " + orderVO.getAmount() + " 元");
            informationPO.setTitle("付款消息");
            informationPO.setUserId(orderVO.getUserId());
            informationPO.setStatus(0);
            informationPO.setObjectId(consumerResult);
            //向用户推送支付消息
            //DaDaoUtil.pushForConsumer(orderVO.getUserId().toString(), "在线支付 " + orderVO.getAmount() + " 元");
            iPushService.pushForConsumer(informationPO.getContent(), informationPO);
        }
        return "SUCCESS";
    }
}
