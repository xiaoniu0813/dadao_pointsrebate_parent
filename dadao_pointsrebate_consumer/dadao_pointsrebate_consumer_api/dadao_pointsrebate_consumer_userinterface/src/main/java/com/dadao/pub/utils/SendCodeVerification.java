package com.dadao.pub.utils;

import com.dadao.user.entity.UserAccount;
import com.dadao.user.service.UserService;
import com.dadao.utils.DaDaoUtil;
import com.dadao.utils.PhoneFormatCheckUtil;
import com.dadao.utils.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 验证发送短信的三种情况
 *
 * @auther NFY niufuyang
 * @create 2017-07-31
 */
public class SendCodeVerification {
    private static final Logger logger = LoggerFactory.getLogger(SendCodeVerification.class);

    public static ResultCode Verification(String phone,int type,int merchant,UserService userService) throws Exception{
        if(type==0){
            UserAccount account = new UserAccount();
            account.setPhone(phone);
            account.setMerchant(merchant);
            boolean result = userService.isExistByPhone(account);
            //判断用户是否存在
            if (result) {
                if (PhoneFormatCheckUtil.isPhoneLegal(phone)) {
                    DaDaoUtil.sendVerifyCode(phone);
                } else {
                    return ResultCode.LOGIN_PHONE_FAIL;
                }
            }else{
                return ResultCode.LOGIN_PHONE_NULL;
            }
        }else if (type==1){
            UserAccount account = new UserAccount();
            account.setPhone(phone);
            account.setMerchant(merchant);
            boolean result = userService.isExistByPhone(account);
            //注册的时候判断手机号是否被注册
            if (result) {
                return ResultCode.REGISTER_EXIST_FAIL;
            } else {
                if (PhoneFormatCheckUtil.isPhoneLegal(phone)) {
                    DaDaoUtil.sendVerifyCode(phone);
                } else {
                    return ResultCode.LOGIN_PHONE_FAIL;
                }
            }
        }else if (type==2){
            UserAccount account = new UserAccount();
            account.setPhone(phone);
            account.setMerchant(merchant);
            boolean result = userService.isExistByPhone(account);
            //找回密码的时候判断该手机号是否存在
            if(result){
                if (PhoneFormatCheckUtil.isPhoneLegal(phone)) {
                    DaDaoUtil.sendVerifyCode(phone);
                } else {
                    return ResultCode.LOGIN_PHONE_FAIL;
                }
            }else{
                return ResultCode.LOGIN_PHONE_NULL;
            }
        }else{
            return ResultCode.SYS_FAIL;
        }
        return ResultCode.SYS_SUCCESS;
    }
}
