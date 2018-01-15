package com.dadao.bank.controller;

import com.dadao.bank.service.impl.IBankServiceImpl;
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
 * 操作银行相关信息
 *
 * @auther NFY niufuyang
 * @create 2017-11-10
 */
@RestController
@RequestMapping(value = "/bank")
public class BankController {
    @Autowired
    private IBankServiceImpl iBankService;

    /**
     * 根据银行编码、开户省编码、开户市编码获取开户银行编码   --NFY
     * @param headBankCode
     * @param provinceCode
     * @param cityCode
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/getBankBranchInfo")
    public Object getBankBranchInfo(String headBankCode, String provinceCode, String cityCode, HttpServletRequest request, HttpServletResponse response){
        return iBankService.getBankBranchInfo(headBankCode,provinceCode,cityCode,request,response);
    }
}
