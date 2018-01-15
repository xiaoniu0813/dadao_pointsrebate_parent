package com.dadao.user.service;

import com.dadao.user.entity.UserAccount;
import com.dadao.user.entity.UserIntegralRecordingPO;
import com.dadao.user.mapper.entity.CurrentIntegral;
import com.dadao.user.mapper.entity.UserDeal;
import com.dadao.user.mapper.entity.UserDealRecord;

import java.util.List;
/**
 * created by GUOYU 2017/11/08
 */
public interface UserDealService {
    /**
     * 根据token查询用户消费情况
     * @param userAccount
     * @return
     */
    UserDealRecord userIntegral(UserIntegralRecordingPO userIntegralRecordingPO, String token, Integer pageNum);

    /**
     *查询当月的积分情况
     * @param status
     * @param token
     * @return
     */
    CurrentIntegral integral(Integer status, String token);
}
