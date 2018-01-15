package com.dadao.user.controller;

import com.dadao.pub.utils.DADAO;
import com.dadao.user.entity.UserAccount;
import com.dadao.user.service.UserService;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yunqiang1 on 2017/7/24.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/isExistByPhone")
    public Object isExistByPhone(UserAccount userAccount, HttpServletRequest request, HttpServletResponse response) {
        boolean flag = userService.isExistByPhone(userAccount);
        return DADAO.encryption(request, response, new Result(!flag ? ResultCode.SYS_SUCCESS : ResultCode.REGISTER_EXIST_FAIL, ""));
    }

    @PostMapping(value = "/register")
    public Object register(UserAccount userAccount, String code, HttpServletRequest request, HttpServletResponse response) {
        return DADAO.encryption(request, response, userService.register(userAccount, code));
    }

    @PostMapping(value = "/login")
    public Object login(UserAccount userAccount, String code, HttpServletRequest request, HttpServletResponse response) {
        return DADAO.encryption(request, response, userService.login(userAccount, code));
    }

    @PostMapping(value = "/shop/check")
    public Object checkBusinessShopExist(@RequestParam(required = true) String phone, HttpServletRequest request, HttpServletResponse response) {
        return DADAO.encryption(request, response, userService.checkBusinessExist(phone));
    }

    @PostMapping(value = "/resetPassword")
    public Object resetPassword(HttpServletRequest request, HttpServletResponse response, UserAccount userAccount, String code) throws Exception {
        boolean result = this.userService.resetPassword(userAccount, code);
        return DADAO.encryption(request, response, new Result(result ? ResultCode.SYS_SUCCESS : ResultCode.SYS_FAIL, ""));
    }

    @PostMapping(value = "/forgetPassword")
    public Object forgetPassword(HttpServletRequest request, HttpServletResponse response, UserAccount userAccount, String code) throws Exception {
        boolean result = this.userService.forgetPassword(userAccount, code);
        return DADAO.encryption(request, response, new Result(result ? ResultCode.SYS_SUCCESS : ResultCode.SYS_FAIL, ""));
    }

    @RequestMapping(value = "/setupPayPassword")
    public Object setupPayPassword(HttpServletRequest request, HttpServletResponse response, UserAccount userAccount) throws Exception {
        boolean result = this.userService.setupPayPassword(userAccount);
        return DADAO.encryption(request, response, new Result(result ? ResultCode.SYS_SUCCESS : ResultCode.SYS_FAIL));
    }

    @RequestMapping(value = "/resetPayPassword")
    public Object resetPayPassword(HttpServletRequest request, HttpServletResponse response, UserAccount userAccount, String code) throws Exception {
        boolean result = this.userService.resetPayPassword(userAccount, code);
        return DADAO.encryption(request, response, new Result(result ? ResultCode.SYS_SUCCESS : ResultCode.SYS_FAIL));
    }

    @RequestMapping(value = "/updatePhone")
    public Object updatePhone(HttpServletRequest request, HttpServletResponse response, UserAccount userAccount, String code) throws Exception {
        return DADAO.encryption(request, response, new Result(userService.updatePhone(userAccount, code)));
    }

    @PostMapping(value = "/judgmentPayPassword")
    public Object judgmentPayPassword(HttpServletRequest request, HttpServletResponse response, String token) {
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, userService.judgmentPayPassword(token)));
    }

    @PostMapping(value = "verificationPayPassword")
    public Object verificationPayPassword(HttpServletRequest request, HttpServletResponse response, String token,String payPassword){
        return DADAO.encryption(request, response, new Result(ResultCode.SYS_SUCCESS, userService.verificationPayPassword(token,payPassword)));
    }

}
