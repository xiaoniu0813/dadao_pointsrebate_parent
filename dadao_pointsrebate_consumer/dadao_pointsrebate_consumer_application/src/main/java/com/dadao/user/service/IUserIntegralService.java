package com.dadao.user.service;

import com.dadao.user.entity.UserAccount;
import com.dadao.user.entity.UserIntegralRecordingPO;
import com.dadao.user.entity.UserIntegralVO;
import com.dadao.utils.Result;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by YunQiang on 2017/8/2
 */
public interface IUserIntegralService {

    /**
     * 查询用户的返利计划
     * @param userAccount
     * @return
     */
    public Result findUserIntegralPlan(UserAccount userAccount);




}
