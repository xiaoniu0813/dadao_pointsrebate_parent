package com.dadao.user.service;

import com.dadao.user.entity.UserInfo;
import com.dadao.utils.BasePage;

/**
 *  Created by GUOYU on 2017-07-16.
 */
public interface UserInfoService {

    BasePage findUserInfoByPage(UserInfo userInfo);

    UserInfo findUser(UserInfo userInfo);
}
