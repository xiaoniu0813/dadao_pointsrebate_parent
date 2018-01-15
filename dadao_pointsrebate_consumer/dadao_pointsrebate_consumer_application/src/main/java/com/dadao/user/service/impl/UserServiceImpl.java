package com.dadao.user.service.impl;

import com.dadao.activities.user.activity.UserActivity;
import com.dadao.user.entity.UserAccount;
import com.dadao.user.service.UserService;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by yunqiang1 on 2017/7/24.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserActivity userActivity;

    public boolean isExistByPhone(UserAccount userAccount) {
        return userActivity.isExistByPhone(userAccount);
    }

    public Result register(UserAccount userAccount, String code) {
        return userActivity.register(userAccount, code);
    }

    public Result login(UserAccount userAccount, String code) {
        return userActivity.login(userAccount, code);
    }

    @Override
    public Result checkBusinessExist(String phone) {
        if (!userActivity.checkShopExist(phone)){
            return new Result(ResultCode.USER_ACCOUNT_NULL,"商户需要先提交资料");
        }
        return new Result(ResultCode.SYS_SUCCESS, "可正常登入商户app");
    }

    public boolean resetPayPassword(UserAccount userAccount, String code) {
        return userActivity.resetPayPassword(userAccount,code);
    }

    public ResultCode updatePhone(UserAccount userAccount, String code) {
        return userActivity.updatePhone(userAccount,code);
    }

    public boolean setupPayPassword(UserAccount userAccount) {
        return userActivity.setupPayPassword(userAccount);
    }

    public boolean resetPassword(UserAccount userAccount, String code) {
        return userActivity.resetPassword(userAccount, code);
    }

    public boolean forgetPassword(UserAccount userAccount, String code) {
        return  userActivity.forgetPassword(userAccount,code);
    }

    public boolean judgmentPayPassword(String token) {
        return userActivity.judgmentPayPassword(token);
    }

    @Override
    public boolean verificationPayPassword(String token, String payPassword) {
        return userActivity.verificationPayPassword(token,payPassword);
    }

}
