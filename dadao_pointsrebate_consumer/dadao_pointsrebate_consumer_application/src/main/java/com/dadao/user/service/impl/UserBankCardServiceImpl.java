package com.dadao.user.service.impl;

import com.dadao.activities.user.activity.UserBankCardActivity;
import com.dadao.user.service.IUserBankCardService;
import com.dadao.utils.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户绑定银行卡
 *
 * @auther NFY niufuyang
 * @create 2017-08-07
 */
@Service
public class UserBankCardServiceImpl implements IUserBankCardService {
    @Autowired
    private UserBankCardActivity userBankCardActivity;

    public QueryResult findUserBankCardList(String token) {
        return userBankCardActivity.findUserBankCardList(token);
    }
}
