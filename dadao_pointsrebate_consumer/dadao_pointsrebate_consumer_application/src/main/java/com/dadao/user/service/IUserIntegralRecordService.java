package com.dadao.user.service;

import com.dadao.user.entity.UserAccount;
import com.dadao.user.entity.UserIntegralRecordingPO;
import com.dadao.user.mapper.entity.UserIntegrals;
import com.dadao.utils.Page;
import com.dadao.utils.PageNew;
import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;

/**
 * Created by YunQiang on 2017/8/2
 */
public interface IUserIntegralRecordService {

    public Result findUserIntegrals(String token, UserIntegrals userIntegrals,Integer pageNum);

    /**
     * 用户当月交易完成数量
     * @param userIntegralRecordingPO
     * @return
     */
    public Integer currentMonthDealCount(UserIntegralRecordingPO userIntegralRecordingPO);

}
