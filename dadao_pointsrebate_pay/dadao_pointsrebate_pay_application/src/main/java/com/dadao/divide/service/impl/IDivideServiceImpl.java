package com.dadao.divide.service.impl;

import com.dadao.divide.service.IDivideService;
import com.dadao.merchants.entity.MerchantsDivided;
import com.dadao.merchants.mapper.IMerchantsDividedMapper;
import com.dadao.order.entity.OrderVO;
import com.dadao.pub.utils.YOPTODADAO;
import com.dadao.yop.service.YeepayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 分账
 *
 * @auther NFY niufuyang
 * @create 2017-11-23
 */
@Service
public class IDivideServiceImpl implements IDivideService {
    @Autowired
    private IMerchantsDividedMapper iMerchantsDividedMapper;

    @Override
    public String divided(OrderVO orderVO, BigDecimal CommissionMoney) throws Exception{
        String parentMerchantNo = YeepayService.getParentMerchantNo();
        String merchantNo = orderVO.getChild_merchant_no();
        //格式化时间精确到毫秒
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        //拼接商户分账请求号
        String divideRequestId = "DIVIDE_" + orderVO.getUserId() + formatter.format(new Date());
        //平台生成订单号
        String orderId = orderVO.getOrderId();
        //易宝生成订单号
        String uniqueOrderNo =orderVO.getChannelSequence();
        //合同号
        String contractNo=orderId+uniqueOrderNo;

        String ledgerNo = YeepayService.getMerchantNo();
        String ledgerName = "大道科技（北京）有限公司";
        String amount=CommissionMoney.toString();
        //分账详情
        String divideDetail = "[{\"ledgerNo\":\""+ledgerNo+"\",\"ledgerName\":\""+ledgerName+"\",\"amount\":\""+amount+"\"}]";

        Map<String, String> params = new HashMap<>();
        params.put("parentMerchantNo", parentMerchantNo);
        params.put("merchantNo", merchantNo);
        params.put("divideRequestId", divideRequestId);
        params.put("orderId", orderId);
        params.put("uniqueOrderNo", uniqueOrderNo);
        params.put("contractNo", contractNo);
        params.put("divideDetail", divideDetail);
        params.put("infoParamExt", "");

        String uri = YeepayService.getUrl(YeepayService.TRADEDIVIDE_URL);
        Map<String, String> result = YOPTODADAO.requestYOP(params, uri, YeepayService.TRADEDIVIDE,YeepayService.TRADEDIVIDE_HMAC);

        String status=result.get("status");
        String message = result.get("message");
        if(message != null){
            System.out.println(message);
            return message ;
        }

        MerchantsDivided merchantsDivided=new MerchantsDivided();
        merchantsDivided.setDivideRequestId(divideRequestId);
        merchantsDivided.setOrderId(orderId);
        merchantsDivided.setUniqueOrderNo(uniqueOrderNo);
        merchantsDivided.setContractNo(contractNo);
        merchantsDivided.setLedgerNo(ledgerNo);
        merchantsDivided.setLedgerName(ledgerName);
        merchantsDivided.setStatus(status);
        merchantsDivided.setDivideDetail(result.get("divideDetail"));
        merchantsDivided.setAmount(new BigDecimal(amount));
        int saveDividedResult=iMerchantsDividedMapper.save(merchantsDivided);
        if(saveDividedResult!=1){
            throw new Exception("保存分账信息失败!");
        }

        System.out.println("status:"+status);
        return status;
    }
}
