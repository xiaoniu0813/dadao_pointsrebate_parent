package com.dadao.merchants.mapper;

import com.dadao.market.entity.MarketGradePO;
import com.dadao.merchants.entity.MerchantsInfo;
import com.dadao.pub.mapper.PayBaseMapper;
import com.dadao.user.entity.UserAccount;
import com.dadao.user.entity.UserInfo;
import com.dadao.user.entity.UserIntegral;
import com.dadao.user.entity.UserWalletPO;

import java.util.List;

/**
 * Created by YunQiang on 2017/11/10
 * @author YunQiang
 * 商户入驻
 */
public interface MerchantsInfoMapper extends PayBaseMapper {
    /**
     * 根据请求号查询数据
     * @param requestNo
     * @return
     */
    MerchantsInfo selectIdByRequestNo(String requestNo);

    /**
     * 更新请求返回信息内容
     * @param merchantsInfo
     * @return
     */
    int updateRequestNotifyInfo(MerchantsInfo merchantsInfo);

    /**
     * 更新回调接口信息
      * @param merchantsInfo
     * @return
     */
    int updateCallBackInfo(MerchantsInfo merchantsInfo);

    /**
     * 审核成功后保存数据
     * @param userAccount
     * @return
     */
    int saveUserAccount(UserAccount userAccount);

    /**
     * 判断手机号是否存在
     * @param userAccount
     * @return
     */
    UserAccount findByPhone(UserAccount userAccount);

    /**
     * 创建钱包
     * @param userWalletPO
     * @return
     */
    int saveUserWallet(UserWalletPO userWalletPO);

    /**
     * 查询类
     * @param marketGradePO
     * @return
     */
    List findMarketGradePOByEntity(MarketGradePO marketGradePO);

    /**
     * 保存用户信息
     * @param userInfo
     * @return
     */
    int saveUserInfo(UserInfo userInfo);

    /**
     * 根据条件查找
     * @param userAccount
     * @return
     */
    List findUserAccountByEntity(UserAccount userAccount);

    /**
     * 创建积分保存
     * @param userIntegral
     * @return
     */
    int saveUserInteger(UserIntegral userIntegral);

    /**
     * 修改账户表手机号
     * @param userAccount
     * @return
     */
    int updateUserAccountPhone(UserAccount userAccount);

    /**
     * 修改用户信息表手机号
     * @param userInfo
     * @return
     */
    int updateUserInfoPhone(UserInfo userInfo);
}
