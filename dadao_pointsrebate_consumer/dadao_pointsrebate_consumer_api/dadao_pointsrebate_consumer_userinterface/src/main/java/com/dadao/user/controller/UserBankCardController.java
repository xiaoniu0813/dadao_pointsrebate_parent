package com.dadao.user.controller;

import com.dadao.pub.utils.DADAO;
import com.dadao.user.entity.UserBankCardVO;
import com.dadao.user.service.IUserBankCardService;
import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户绑定银行卡
 *
 * @auther NFY niufuyang
 * @create 2017-08-07
 */
@RestController
public class UserBankCardController {

    private Logger logger = LoggerFactory.getLogger(UserBankCardController.class);
    @Autowired
    private IUserBankCardService userBankCardService;

    /**
     * 获取用户绑定卡信息
     * @param token
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/findUserBankCardList")
    public Object findUserBankCardList(String token, HttpServletRequest request, HttpServletResponse response) {
        QueryResult QueryResult = userBankCardService.findUserBankCardList(token);
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, QueryResult));
    }
}
