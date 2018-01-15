package com.dadao.temp.mapper;

import com.dadao.pub.mapper.BaseMapper;

import java.util.HashMap;

/**
 * Created by YunQiang on 2017/8/16
 */
public interface ITempMapper extends BaseMapper{

    //删除用户注册
    public int delUserAccount(HashMap hashMap);

    //删除用户信息
    public int delUserInfo(HashMap hashMap);

    //删除用户积分,商户没有
    public int delUserIntegral(HashMap hashMap);

    //删除用户钱包
    public int delUserWallet(HashMap hashMap);


}
