package com.dadao.bankshorthand.controller;

import com.dadao.bankshorthand.entity.BankShorthand;
import com.dadao.bankshorthand.service.IBankShorthandService;
import com.dadao.util.DADAO;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auther NFY niufuyang
 * @create 2017-10-25
 */
@RestController
public class BankShorthandController {
    @Autowired
    private IBankShorthandService service;

    @PostMapping(value = "/findByShorthand")
    public Object findByShorthand(BankShorthand bankShorthand, HttpServletRequest request, HttpServletResponse response) {
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS,service.findByShorthand(bankShorthand) ));
    }
}
