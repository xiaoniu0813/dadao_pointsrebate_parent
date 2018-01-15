package com.dadao.balance.service.impl;

import com.dadao.balance.service.IBalanceService;
import com.dadao.trade.mapper.TradeMapper;
import com.dadao.utils.ArithUtil;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import com.dadao.yop.service.YeepayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 余额查询
 *
 * @auther NFY niufuyang
 * @create 2017-11-13
 */
@Service
public class IBalanceServiceImpl implements IBalanceService {

    @Autowired
    private TradeMapper tradeMapper;

    /**
     * 查询商户余额
     * @return
     */
    @Override
    public BigDecimal findBalance(String token) {

        String parentMerchantNo = YeepayService.getParentMerchantNo();
        //根据token找到子商编
        String merchantNo = tradeMapper.findMerchantNoByToken(token);

        Map<String, String> params = new HashMap<>();

        params.put("parentMerchantNo", parentMerchantNo);
        params.put("merchantNo", merchantNo);

        String url = YeepayService.getUrl(YeepayService.BALANCEQUERY_URL);
        Map<String, String>  result = YeepayService.requestYOP(params, url, YeepayService.BALANCEQUERY);

        //String returnMsg = formatString(result.get("returnMsg"));
        String returnCode = formatString(result.get("returnCode"));
        String merBalance = formatString(result.get("merBalance"));
        if(returnCode.equals("REG00000")) {
            return ArithUtil.roundDown(new BigDecimal(merBalance),2);
        }else{
            return new BigDecimal(0);
        }
    }

    @Override
    public Result queryBusinessBalance(String token) {
        //根据token找到子商编
        String merchantNo = tradeMapper.findMerchantNoByToken(token);
        //拿到配置文件中的父商编
        String parentMerchantNo = YeepayService.getParentMerchantNo();
        //请求易宝
        Map<String, String> params = new HashMap<>();
        params.put("parentMerchantNo", parentMerchantNo);
        params.put("merchantNo", merchantNo);
        Map<String, String> result = new HashMap<>();
        String url = YeepayService.getUrl(YeepayService.BALANCEQUERY_URL);
        result = YeepayService.requestYOP(params, url, YeepayService.BALANCEQUERY);

        return "REG00000".equals(result.get("returnCode").trim()) ? new Result(ResultCode.SYS_SUCCESS,result) : new Result(ResultCode.YOP_ERROR_MSG, result);
    }

    String formatString(String text) {
        return text == null ? "" : text.trim();
    }
}
