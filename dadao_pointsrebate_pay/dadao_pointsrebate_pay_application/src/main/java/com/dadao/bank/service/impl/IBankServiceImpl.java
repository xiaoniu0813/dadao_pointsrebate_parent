package com.dadao.bank.service.impl;

import com.dadao.bank.service.IBankService;
import com.dadao.pub.utils.DADAO;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import com.dadao.yop.service.YeepayService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 操作银行相关信息接口
 *
 * @auther NFY niufuyang
 * @create 2017-11-10
 */
@Service
public class IBankServiceImpl implements IBankService {

    @Override
    public Object getBankBranchInfo(String headBankCode, String provinceCode, String cityCode, HttpServletRequest request, HttpServletResponse response) {
        Map map= YeepayService.getBankBranchInfo(headBankCode,provinceCode,cityCode);
        System.out.println("-->"+map);
         if(map.get("branchBankInfo")!=null){
             return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS , map.get("branchBankInfo")));
         }else{
             return DADAO.encryption(request, response, new Result(ResultCode.SYS_FAIL , map.get("returnMsg")));
         }
    }
}
