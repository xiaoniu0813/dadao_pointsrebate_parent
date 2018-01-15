package com.dadao.user.service.impl;

import com.dadao.user.activity.UserIntegralActivity;
import com.dadao.user.entity.UserIntegral;
import com.dadao.user.service.IUserIntegralService;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户积分
 *
 * @auther NFY niufuyang
 * @create 2017-8-24
 */
@Service
public class UserIntegralServiceImpl implements IUserIntegralService {
    @Autowired
    private UserIntegralActivity userIntegralActivity;


    public QueryResult listUserIntegral(UserIntegral userIntegral) {
        return userIntegralActivity.listUserIntegral(userIntegral);
    }
}
