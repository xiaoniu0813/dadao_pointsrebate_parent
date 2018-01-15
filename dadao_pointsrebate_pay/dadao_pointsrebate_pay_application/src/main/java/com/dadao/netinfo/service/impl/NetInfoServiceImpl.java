package com.dadao.netinfo.service.impl;

import com.dadao.merchants.entity.MerchantsInfo;
import com.dadao.netinfo.service.NetInfoService;
import com.dadao.utils.DaDaoUtil;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import com.dadao.yop.service.YeepayService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * created by GUOYU 2017/11/20
 */
@Service
public class NetInfoServiceImpl implements NetInfoService {

    @Override
    public Result netStatus(MerchantsInfo merchantsInfo) {
        //获取父编号
        merchantsInfo.setParentMerchantNo(YeepayService.getParentMerchantNo());
        Map<String,String> result = new HashMap<>();
        //获取入网信息查询url
        String url = YeepayService.getUrl(YeepayService.REGSTATUSQUERY_URL);
        //获取返回结果
        result = YeepayService.requestYOP(DaDaoUtil.objToMapStr(merchantsInfo),url,YeepayService.REGSTATUSQUERY);
        if("REG00000".equals(result.get("returnCode"))){
            return new Result(ResultCode.SYS_SUCCESS,result);
        }
        return  new Result(ResultCode.SYS_FAIL,result);
    }
}
