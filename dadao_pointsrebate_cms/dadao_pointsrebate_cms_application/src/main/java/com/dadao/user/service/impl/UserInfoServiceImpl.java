package com.dadao.user.service.impl;

import com.dadao.user.activity.UserInfoActivity;
import com.dadao.user.entity.UserInfo;
import com.dadao.user.service.UserInfoService;
import com.dadao.utils.BasePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  Created by GUOYU on 2017-07-16.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService{

    @Autowired
    private UserInfoActivity userInfoActivity;
    public BasePage findUserInfoByPage(UserInfo userInfo) {
        return userInfoActivity.findUserInfoByPage(userInfo);
    }

    public UserInfo findUser(UserInfo userInfo) {
        return userInfoActivity.findUser(userInfo);
    }
}
