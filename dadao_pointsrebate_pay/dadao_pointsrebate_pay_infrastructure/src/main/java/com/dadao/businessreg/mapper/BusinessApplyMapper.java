package com.dadao.businessreg.mapper;


import com.dadao.businessreg.entity.BusinessApply;
import com.dadao.merchants.entity.MerchantsInfo;
import com.dadao.pub.mapper.BaseMapper;
import com.dadao.user.entity.UserAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author GUOYU 2017/12/11
 */
public interface BusinessApplyMapper extends BaseMapper {
    /**
     * 根据token查询信息
     * @param token
     * @return
     */
    UserAccount findUserAccountByToken(String token);

    /**
     * 查询对应数据
     * @param id
     * @return
     */
    MerchantsInfo findMerchantsInfo(Long id);

    /**
     * 将相关信息保存
     * @param businessApply
     * @return
     */
    int saveBusinessApply(BusinessApply businessApply);

    /**
     * 修改business_apply
     * @param businessApply
     * @return
     */
    int updateBusinessApply(BusinessApply businessApply);

    /**
     * 根据分页 状态查询商户注册信息
     * @param businessApply
     * @return
     */
    List<BusinessApply> findUserRegisterInfo(BusinessApply businessApply);

    /**
     * 根据条件查询总记录数
     * @param businessApply
     * @return
     */
    Long totalSize(BusinessApply businessApply);

    /**
     * 查找单条数据
     * @param businessApply
     */
    BusinessApply findSingletonData(BusinessApply businessApply);

    /**
     * 查找单条数据
     * @param businessApply
     * @return
     */
    BusinessApply findInfo(BusinessApply businessApply);

    /**
     * 查找出总数
     * @param businessApply
     * @return
     */
    List findByAll(com.dadao.shop.entity.BusinessApply businessApply);




}
