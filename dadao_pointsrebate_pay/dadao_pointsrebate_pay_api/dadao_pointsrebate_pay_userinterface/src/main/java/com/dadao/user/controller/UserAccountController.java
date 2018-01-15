package com.dadao.user.controller;

import com.dadao.user.entity.UserAccount;
import com.dadao.user.service.IUserAccountService;
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
 * @create 2017-11-8
 */
@RestController
public class UserAccountController {
    @Autowired
    private IUserAccountService service;

    @PostMapping(value = "/findUserAccountByUserId")
    public Object findUserAccountByUserId(HttpServletRequest request, HttpServletResponse response, UserAccount userAccount){
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS , service.findByUserId(userAccount.getUserId())));
    }
}
