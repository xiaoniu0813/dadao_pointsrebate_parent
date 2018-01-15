package com.dadao.transactionrecod.controller;

import com.dadao.pub.utils.DADAO;
import com.dadao.transactionrecod.service.ITransactionRecodService;
import com.dadao.user.entity.UserTransactionRecodPO;
import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户交易记录
 *
 * @auther NFY niufuyang
 * @create 2017-08-02
 */
@RestController
public class TransactionRecodController {
    @Autowired
    private ITransactionRecodService transactionRecodService;

    @PostMapping(value = "/UserBalanceRecod")
    private Object findUserBalanceRecod(@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,UserTransactionRecodPO userTransactionRecodPO, String token, HttpServletRequest request, HttpServletResponse response) throws Exception{
        userTransactionRecodPO.setBeginIndex((pageNum - 1) * userTransactionRecodPO.getPageSize());
        QueryResult result = transactionRecodService.findUserBalanceRecod(userTransactionRecodPO, token);
        if (result!=null)
            result.setPageNum(pageNum);
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS,result));
    }
}
