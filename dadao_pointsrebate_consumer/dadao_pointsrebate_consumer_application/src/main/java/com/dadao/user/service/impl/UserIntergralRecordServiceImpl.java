package com.dadao.user.service.impl;

import com.dadao.activities.user.activity.UserIntegralRecordActivity;
import com.dadao.user.entity.UserAccount;
import com.dadao.user.entity.UserIntegralRecordingPO;
import com.dadao.user.mapper.entity.UserIntegrals;
import com.dadao.user.service.IUserIntegralRecordService;
import com.dadao.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by YunQiang on 2017/8/2
 */
@Service
public class UserIntergralRecordServiceImpl implements IUserIntegralRecordService{
    @Autowired
    private UserIntegralRecordActivity userIntegralRecordActivity;

    public Result findUserIntegrals(String token, UserIntegrals userIntegrals, Integer pageNum){
        QueryResult queryResult = null;
        try {
            queryResult = userIntegralRecordActivity.findUserIntegrals(token, userIntegrals,pageNum);
            return new Result(ResultCode.SYS_SUCCESS, queryResult);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.SYS_FAIL,"未知错误，请联系管理员!");
        }
    }
    //当月用户交易完成数量
    @Override
    public Integer currentMonthDealCount(UserIntegralRecordingPO userIntegralRecordingPO) {
        return userIntegralRecordActivity.currentMonthDealCount(userIntegralRecordingPO);
    }
}
