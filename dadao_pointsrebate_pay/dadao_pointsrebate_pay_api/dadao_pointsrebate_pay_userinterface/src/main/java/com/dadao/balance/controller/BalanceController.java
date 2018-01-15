package com.dadao.balance.controller;

import com.dadao.balance.service.IBalanceService;
import com.dadao.util.DADAO;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 查询余额
 *
 * @auther NFY niufuyang
 * @create 2017-11-13
 */
@RestController
@RequestMapping(value = "balance")
public class BalanceController {

    @Autowired
    private IBalanceService iBalanceService;

    @PostMapping(value = "findBalance")
    public Object findBalance(String token, HttpServletRequest request, HttpServletResponse response){
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS , iBalanceService.findBalance(token)));
    }

    @PostMapping(value = "query")
    public Object query(String token, HttpServletRequest request, HttpServletResponse response){
        return iBalanceService.queryBusinessBalance(token);
    }

}
