package com.dadao.user.controller;

import com.dadao.pub.utils.DADAO;
import com.dadao.user.entity.UserAccount;
import com.dadao.user.entity.UserIntegralRecordingPO;
import com.dadao.user.entity.UserIntegralVO;
import com.dadao.user.service.IUserIntegralService;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by YunQiang on 2017/8/2
 */
@RestController
@RequestMapping("/user/cashback")
public class UserIntegralController {

    @Autowired
    private IUserIntegralService iUserIntegralService;

    @PostMapping(value = "/plan")
    public Object isExistByPhone(UserAccount userAccount, HttpServletRequest request, HttpServletResponse response) {
        return DADAO.encryption(request, response, iUserIntegralService.findUserIntegralPlan(userAccount));
    }


}
