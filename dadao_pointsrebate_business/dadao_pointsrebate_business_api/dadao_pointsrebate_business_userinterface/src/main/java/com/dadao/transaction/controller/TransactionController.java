package com.dadao.transaction.controller;

import com.dadao.transaction.service.TransactionService;
import com.dadao.user.entity.UserAccount;
import com.dadao.util.BaseController;
import com.dadao.util.DADAO;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class TransactionController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    /**
     * @Author: yangrui
     * @Description: 查询商铺收款记录
     * @Date: 下午5:40 2017/7/30
     */
    @PostMapping(value = "transaction/findByEntity")
    public Object findByEntity(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        UserAccount userAccount = getUserAccount(httpServletRequest);
        Long userId = userAccount.getUserId();
        return DADAO.encryption(httpServletRequest, httpServletResponse, new Result(ResultCode.SYS_SUCCESS, transactionService.findByEntity(userId)));
    }

    /**
     * @Author: yangrui
     * @Description: 查询商铺收款详情
     * @Date: 下午2:34 2017/8/13
     */
    @PostMapping(value = "transaction/{transactionId}/findById")
    public Object findById(@PathVariable Long transactionId, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return DADAO.encryption(httpServletRequest, httpServletResponse, new Result(ResultCode.SYS_SUCCESS, transactionService.findById(transactionId)));
    }
}
