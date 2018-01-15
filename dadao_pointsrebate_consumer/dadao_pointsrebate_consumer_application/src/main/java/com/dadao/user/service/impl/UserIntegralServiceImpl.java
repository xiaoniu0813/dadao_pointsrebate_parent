package com.dadao.user.service.impl;

import com.dadao.activities.user.activity.UserIntegralActivity;
import com.dadao.user.entity.UserAccount;
import com.dadao.user.entity.UserIntegralRecordingPO;
import com.dadao.user.entity.UserIntegralVO;
import com.dadao.user.service.IUserIntegralService;
import com.dadao.utils.Result;
import com.dadao.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by YunQiang on 2017/8/2
 */
@Service
public class UserIntegralServiceImpl implements IUserIntegralService{


    @Autowired
    private UserIntegralActivity userIntegralActivity;

    public Result findUserIntegralPlan(UserAccount userAccount) {
        List list = null;
        try {
            list = userIntegralActivity.findUserIntegralPlan(userAccount);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.SYS_FAIL,"请联系管理员!");
        }
        return new Result(ResultCode.SYS_SUCCESS, list);
    }





}
