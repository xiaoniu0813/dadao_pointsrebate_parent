package com.dadao.user.mapper;

import com.dadao.pub.mapper.BaseMapper;
import com.dadao.user.entity.UserAccount;
import com.dadao.user.entity.UserIntegral;
import com.dadao.user.entity.UserIntegralVO;

import java.util.HashMap;
import java.util.List;

/**
 * Created by YunQiang on 2017/8/2
 */
public interface IUserIntegralMapper extends BaseMapper{

    /**
     * 查询用户是否有积分记录
     * @param userAccount
     * @return
     */
    public Integer ifIntegralNull(UserAccount userAccount);

    /**
     * 带有用户积分的返利计划
     * @param userAccount
     * @return
     */
    public List<HashMap> findIntegralNotNull(UserAccount userAccount);

    /**
     * 返利计划
     * @return
     */
    public List<HashMap> findIntegralIsNull();

    /**
     * 查询市场ids,用来初始化用户积分钱包
     */
    public List findMarketIds();

    /**
     * 根据用户ID查找
     * @param userId
     * @return
     */
    public UserIntegralVO findByUserID(Long userId);

    /**
     * 获取用户总积分
     * @param userIntegralVO
     * @return
     */
    public UserIntegralVO findIntegral(UserIntegralVO userIntegralVO);

}
