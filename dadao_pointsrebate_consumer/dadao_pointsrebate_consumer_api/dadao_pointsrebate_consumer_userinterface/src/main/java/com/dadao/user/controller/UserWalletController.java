package com.dadao.user.controller;

import com.dadao.cashback.entity.BalanceAndFreeze;
import com.dadao.pub.utils.DADAO;
import com.dadao.user.entity.UserWalletVO;
import com.dadao.user.service.IUserWalletService;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 用户余额
 *
 * @auther NFY niufuyang
 * @create 2017-08-07
 */
@RestController
public class UserWalletController {
    private Logger logger = LoggerFactory.getLogger(UserWalletController.class);
    @Autowired
    private IUserWalletService userWalletService;

    /**
     * 查询账户可用余额
     *
     * @param token
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/findUserWallet")
    public Object findUserWallet(String token, HttpServletRequest request, HttpServletResponse response) {
        BalanceAndFreeze balanceAndFreeze = userWalletService.findBalance(token);
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, balanceAndFreeze));
    }

}
