package com.dadao.user.service;

import com.dadao.user.entity.UserAccount;

import java.util.Map;

import com.dadao.user.entity.UserAccount;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;

/**
 * Created by yunqiang1 on 2017/7/24.
 */
public interface UserService {

    /**
     * 1.判断手机号是否已经被注册
     * @param userAccount
     * @return
     */
    public boolean isExistByPhone(UserAccount userAccount);

    /**
     * 2.保存注册用户
     * @param userAccount
     * @return
     */
    public Result register(UserAccount userAccount, String code);

    /**
     * 3.用户登录
     * @param userAccount
     * @return
     */
    Result login(UserAccount userAccount, String Code);

    /**
     *检查商户是否审核通过
     * @return
     */
    Result checkBusinessExist(String phone);

    //重置支付密码
    boolean resetPayPassword(UserAccount userAccount, String code);

    //更新手机绑定
    ResultCode updatePhone(UserAccount userAccount, String code);

    //设置支付密码
    boolean setupPayPassword(UserAccount userAccount);

    //重置登陆密码
    boolean resetPassword(UserAccount userAccount, String code);

    //忘记密码
    boolean forgetPassword(UserAccount userAccount,String code);

    boolean judgmentPayPassword(String token);

    boolean verificationPayPassword(String token,String payPassword);


}
