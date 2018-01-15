package com.dadao.trade.service.impl;

import com.dadao.capitalpool.entity.CapitalpoolPO;
import com.dadao.capitalpool.entity.CapitalpoolRecordPO;
import com.dadao.order.entity.OrderPO;
import com.dadao.push.entity.InformationPO;
import com.dadao.push.service.IPushService;
import com.dadao.refunds.entity.RefundsApplicationPO;
import com.dadao.trade.mapper.RefundResultMapper;
import com.dadao.trade.mapper.TradeMapper;
import com.dadao.trade.mapper.entity.RefundResult;
import com.dadao.trade.service.TradeService;
import com.dadao.trade.service.entity.RefundPO;
import com.dadao.user.entity.UserIntegralRecordingPO;
import com.dadao.user.entity.UserTransactionRecodPO;
import com.dadao.utils.DaDaoUtil;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import com.dadao.yop.service.YeepayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by YunQiang on 2017/11/21
 * @author YunQiang
 */
@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    private RefundResultMapper refundResultMapper;

    @Autowired
    private TradeMapper tradeMapper;

    @Autowired
    private IPushService iPushService;


    @Override
    @Transactional
    public Result refund(int status, String platformDescription, Long ...ids) {
        Map<String, Boolean> dealResult = new HashMap<>(ids.length);
        for (Long id :ids) {
            RefundsApplicationPO refundsApplicationPO = new RefundsApplicationPO();
            refundsApplicationPO.setStatus(status);
            refundsApplicationPO.setOrderId(id);
            refundsApplicationPO.setPlatformDescription(platformDescription);
            if(status == 3){
                refundsApplicationPO.setProcessDetails("平台审核通过");
            }
            if(status == 4){
                refundsApplicationPO.setProcessDetails("平台审核未通过");
            }
            int updateCount = tradeMapper.updateRefundsApplicationById(refundsApplicationPO);
            if (status == 4){
                return updateCount == 1 ? new Result(ResultCode.SYS_SUCCESS, "更新了" + updateCount + "数据") : new Result(ResultCode.SYS_FAIL, "出Bug了一不小心更新了" + updateCount +"条数据");
            }
            //根据订单id，拿到需要退款的信息
            OrderPO order = tradeMapper.selectOrderById(id);
            /**组装退款对象**/
            RefundPO refundPO = new RefundPO();
            //原订单号
            refundPO.setOrderId(order.getOrderId());
            //易宝流水号
            refundPO.setUniqueOrderNo(order.getChannelSequence());
            //退款请求号
            refundPO.setRefundRequestId("R" + order.getChannelRefundSequence() + System.currentTimeMillis() + "");
            //退款金额(全额退款)
            refundPO.setRefundAmount(order.getAmount().toString());
            //自定义对账科目(暂无) refundPO.setMemo();
            //获取父商编并保存到参数
            refundPO.setNotifyUrl(YeepayService.getRefundUrl());
            refundPO.setParentMerchantNo(YeepayService.getParentMerchantNo());
            refundPO.setMerchantNo(order.getChild_merchant_no());
            //将实体类转换为map
            Map<String, String> params = DaDaoUtil.objToMapStr(refundPO);
            Map<String, String> result = new HashMap<>();
            String uri = YeepayService.getUrl(YeepayService.REFUND_URL);
            result = YeepayService.requestYOP(params, uri, YeepayService.REFUND, YeepayService.REFUND_HMAC);
            //如果退款申请成功，将退款信息写入数据库
            String codeFlag = "OPR00000";
            if (codeFlag.equals(result.get("code").trim())){
                OrderPO resultData = new OrderPO();
                //保存返回数据
                resultData.setChannelResponse(result.toString());
                //退款状态
                resultData.setChannelRetCode(result.get("status"));
                //订单状态(退款申请成功，退款会很快给用户)
                resultData.setOrderStatus(9);
                //退款渠道方单号
                resultData.setChannelRefundSequence(result.get("refundRequestId"));
                //修改时间
                resultData.setUpdateTime(new Date());
                //设置id
                resultData.setId(id);
                tradeMapper.updateOrderById(resultData);
                //为用户推送退款消息
                InformationPO informationPO = new InformationPO();
                informationPO.setTitle("退款审核成功");
                informationPO.setContent("您的\"" + order.getProduct_name() + "\"申请" + order.getAmount() + "元退款成功, 系统将会在24小时内为您原路退款！");
                informationPO.setUserId(order.getUserId());
                informationPO.setHaveRead(0);
                informationPO.setStatus(3);
                informationPO.setObjectId(order.getId());
                iPushService.pushForConsumer(informationPO.getContent(), informationPO);
            }
            //批量处理结果
            dealResult.put(id.toString(), codeFlag.equals(result.get("code").trim()));
        }
        return new Result(ResultCode.SYS_SUCCESS, dealResult);
    }

    @Override
    public Result refundQuery(Long id) {
        //根据订单id，拿到需要退款的信息
        OrderPO order = tradeMapper.selectOrderById(id);
        String parentMerchantNo = YeepayService.getParentMerchantNo();
        Map<String, String> params = new HashMap<>();
        params.put("parentMerchantNo", parentMerchantNo);
        params.put("merchantNo", order.getChild_merchant_no());
        params.put("refundRequestId", order.getChannelRefundSequence());
        params.put("orderId", order.getOrderId());
        params.put("uniqueRefundNo", order.getRefundSequence());

        Map<String, String> result = new HashMap<>();
        String uri = YeepayService.getUrl(YeepayService.REFUNDQUERY_URL);
        result = YeepayService.requestYOP(params, uri, YeepayService.REFUNDQUERY, YeepayService.REFUNDQUERY_HMAC);
        return result.get("code").equals("OPR00000") ? new Result(ResultCode.SYS_SUCCESS, result) : new Result(ResultCode.YOP_ERROR_MSG, result);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String refundResult(String response) {
        try {
            //解密易宝通知,并赋值到实体类
            Map<String, String> result = YeepayService.callback(response);
            RefundResult refundResult = new RefundResult();
            refundResult.setParentMerchantNo(result.get("parentMerchantNo"));
            refundResult.setMerchantNo(result.get("merchantNo"));
            refundResult.setOrderId(result.get("orderId"));
            refundResult.setUniqueOrderNo(result.get("uniqueOrderNo"));
            refundResult.setOrderAmount(result.get("orderAmount"));
            refundResult.setResidualAmount(result.get("residualAmount"));
            refundResult.setRefundAmount(result.get("refundAmount"));
            refundResult.setRefundRequestId(result.get("refundRequestId"));
            refundResult.setUniqueRefundNo(result.get("uniqueRefundNo"));
            refundResult.setDescription(result.get("description"));
            refundResult.setRefundRequestDate(result.get("refundRequestDate"));
            refundResult.setStatus(result.get("status"));
            refundResult.setRefundSuccessDate(result.get("refundSuccessDate"));
            refundResult.setErrorMessage(result.get("errorMessage"));
            //将退款结果保存到数据库
            OrderPO orderPO = new OrderPO();
            orderPO.setOrderId(refundResult.getOrderId());
            orderPO.setChannelRetCode(refundResult.getStatus());
            //退款拒绝
            String status = "REJECT";
            if (status.equals(refundResult.getStatus())){
                orderPO.setOrderStatus(8);
                orderPO.setDescription("支付系统退款拒绝");
                orderPO.setChannelRetCode(status);
            }
            //处理中
            status = "PROCESSING";
            if (status.equals(refundResult.getStatus())){
                orderPO.setOrderStatus(7);
                orderPO.setDescription("处理中");
            }
            //退款中断
            status = "SUSPEND";
            if (status.equals(refundResult.getStatus())){
                orderPO.setOrderStatus(8);
                orderPO.setDescription("退款中断");
            }
            //退款成功
            status = "SUCCESS";
            if (status.equals(refundResult.getStatus())){
                orderPO.setDescription("退款成功");
                orderPO.setOrderStatus(9);
            }
            //退款撤销
            status = "CANCEL";
            if (status.equals(refundResult.getStatus())){
                orderPO.setDescription("退款撤销");
                orderPO.setOrderStatus(8);
            }
            //更新时间
            orderPO.setUpdateTime(new Date());
            refundResultMapper.insertRefundResult(orderPO);

            //如果退款成功
            String flag = "SUCCESS";
            if(flag.equals(refundResult.getStatus())){
                //拿到订单
                OrderPO order = refundResultMapper.selectByOrderId(refundResult.getOrderId());
                //通过serialNumber找到原始钱包流水记录
                UserTransactionRecodPO userTransactionRecodPO = tradeMapper.selectUserTransactionRecodById(order.getChannelSequence());
                //消减用户积分并产生一条积分流水记录
                dealWithIntegral(orderPO);
                //消减商户钱包，并产生一条交易流水记录
                dealWithWallet(order.getUserId(), userTransactionRecodPO);
                //消减资金池余额，并产生一条资金池流水记录
                dealWithCapitalpool(orderPO,userTransactionRecodPO);
            }
            return "SUCCESS";
        }catch (Exception e){
            return "FAILED >>" + e.getMessage();
        }
    }


    /**
     * 消减用户积分并保存数据
     * @param orderPO 订单表实体类
     */
    private void dealWithIntegral(OrderPO orderPO){
        //找到积分表主键
        Long id = tradeMapper.findIntegralId(orderPO.getMarketId().longValue(), orderPO.getUserId());
        //通过订单表拿到原始积分记录
        UserIntegralRecordingPO recordingPO = tradeMapper.findUserIntegralRecordingByOrderId(orderPO.getId());
        //将原始主键改为空，以便利用这个实体类插入新的记录
        recordingPO.setIRid(null);
        //消减用户积分
        tradeMapper.minusUserIntegral(orderPO.getMarketId().longValue(), orderPO.getUserId(), recordingPO.getIntegral());
        //积分发生方向为减少
        recordingPO.setDirection(1);
        //创建一条积分记录
        tradeMapper.createUserIntegralRecording(recordingPO);
    }

    /**
     * 消减用户钱包余额并保存记录
     * @param userId 商户的userId
     */
    private void dealWithWallet(Long userId, UserTransactionRecodPO userTransactionRecodPO){
        //删除主键
        userTransactionRecodPO.setTransactionId(null);
        //交易类型为退款
        userTransactionRecodPO.setTransactionType(5);
        //交易详情为退款
        userTransactionRecodPO.setTransactionDetails("退款");
        //支出收入类型。0支出，1收入
        userTransactionRecodPO.setExpenditureIncome(0);
        //消减商户钱包余额
        tradeMapper.minusUserWallet(userId, userTransactionRecodPO.getActualIncome());
        //保存退款记录
        tradeMapper.createUserTransactionRecod(userTransactionRecodPO);
    }

    private void dealWithCapitalpool(OrderPO orderPO, UserTransactionRecodPO userTransactionRecodPO){
        //消减资金池余额
        CapitalpoolPO capitalpoolPO = new CapitalpoolPO();
        //几级市场
        capitalpoolPO.setMarketId(orderPO.getMarketId().longValue());
        //减多少金额
        capitalpoolPO.setMoney(userTransactionRecodPO.getCommission());
        tradeMapper.minusCapitalpool(capitalpoolPO);
        //产生一条资金池流水记录
        CapitalpoolRecordPO record = new CapitalpoolRecordPO();
        //保存订单id
        record.setObjectId(orderPO.getId());
        //交易类型为退款
        record.setStatus(1);
        //发生金额
        record.setMoney(capitalpoolPO.getMoney());
        record.setMarketId(orderPO.getMarketId().longValue());
        //描述
        record.setDescription("退款");
        //保存记录
        tradeMapper.createCapitalpoolRecord(record);
    }

}
