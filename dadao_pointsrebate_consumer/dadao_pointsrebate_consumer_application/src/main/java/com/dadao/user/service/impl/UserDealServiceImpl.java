package com.dadao.user.service.impl;

import com.dadao.activities.user.activity.UserDealActivity;
import com.dadao.user.entity.UserAccount;
import com.dadao.user.entity.UserIntegralRecordingPO;
import com.dadao.user.mapper.entity.CurrentIntegral;
import com.dadao.user.mapper.entity.UserDeal;
import com.dadao.user.mapper.entity.UserDealRecord;
import com.dadao.user.service.UserDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * created by GUOYU 2017/11/08
 */
@Service
public class UserDealServiceImpl implements UserDealService {
    @Autowired
    private UserDealActivity userDealActivity;
    @Override
    public UserDealRecord userIntegral(UserIntegralRecordingPO userIntegralRecordingPO, String token, Integer pageNum) {
        return userDealActivity.userIntegral(userIntegralRecordingPO,token,pageNum);
    }

    @Override
    public CurrentIntegral integral(Integer status, String token) {
        return userDealActivity.integral(status,token);
    }
}
