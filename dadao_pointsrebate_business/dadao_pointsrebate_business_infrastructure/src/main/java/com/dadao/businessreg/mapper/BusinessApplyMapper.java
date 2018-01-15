package com.dadao.businessreg.mapper;


import com.dadao.businessreg.entity.BusinessApply;
import com.dadao.merchants.entity.MerchantsInfo;
import com.dadao.pub.mapper.BaseMapper;
import com.dadao.user.entity.UserAccount;

/**
 * @Author GUOYU 2017/12/11
 */
public interface BusinessApplyMapper extends BaseMapper {
    /**
     * 根据token查询信息
     * @param userAccount
     * @return
     */
    UserAccount findUserAccountByToken(UserAccount userAccount);

    /**
     * 根据请求号手机号查询对应数据
     * @param businessApply
     * @return
     */
    BusinessApply findMerchantsInfo(BusinessApply businessApply);
    /**
     * 将相关信息保存
     * @param businessApply
     * @return
     */
    int saveBusinessApply(BusinessApply businessApply);

    /**
     * 根据商户id查看状态
     * @param businessApply
     * @return
     */
    BusinessApply findByUserId(BusinessApply businessApply);

    /**
     * 修改信息yb_merchants_info
     * @param businessApply
     * @return
     */
    int updateMerchanrsInfo(BusinessApply businessApply);

    /**
     *  修改business_apply
     * @param businessApply
     * @return
     */
    int updateBusinessApply(BusinessApply businessApply);

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    MerchantsInfo findByMerchantInfo(Long id);

    /**
     * 根据userId查询数据
     * @param userId
     * @return
     */
    BusinessApply findBusinessApplyByUserId(Long userId);

    /**
     * 根据请求号查找
     * @param requestNo
     * @return
     */
    MerchantsInfo findByRequestNo(String requestNo);

}
