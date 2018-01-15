package com.dadao.cashback.controller;

import com.dadao.cashback.entity.CashbackDetailsVO;
import com.dadao.cashback.service.ICashbackDetailsService;
import com.dadao.pub.utils.DADAO;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by YunQiang on 2017/7/28
 */
@RestController
@RequestMapping("/cashback")
public class CashbackDetailsController {

    @Autowired
    private ICashbackDetailsService cashbackDetailsService;

    /**
     * 通过id查找返现明细
     * @param cashbackDetailsVO
     * @param request
     * @param response
     */

    @PostMapping(value = "/list")
    public Object findById(CashbackDetailsVO cashbackDetailsVO, String token, @RequestParam(value="pageNum", defaultValue = "1") Integer pageNum, HttpServletRequest request, HttpServletResponse response){
        Object result = cashbackDetailsService.findByStatus(cashbackDetailsVO, token, pageNum);
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS , result ));
    }

    @PostMapping(value = "/countCashback")
    public Object findMessageByStatus(CashbackDetailsVO cashbackDetailsVO, String token,HttpServletRequest request, HttpServletResponse response){
        Map result = cashbackDetailsService.findMessageByStatus(cashbackDetailsVO, token);
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS , result));
    }

}
