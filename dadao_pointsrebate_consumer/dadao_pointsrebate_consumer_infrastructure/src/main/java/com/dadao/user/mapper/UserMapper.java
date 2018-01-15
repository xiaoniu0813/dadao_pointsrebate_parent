package com.dadao.user.mapper;

import com.dadao.pub.mapper.BaseMapper;
import com.dadao.shop.entity.BusinessApply;
import com.dadao.user.entity.UserAccount;
import org.apache.ibatis.annotations.Param;

/**
 * Created by yunqiang on 2017/7/24.
 */
public interface UserMapper extends BaseMapper {
    UserAccount findByToken(UserAccount userAccount);
    UserAccount findByPhone(UserAccount userAccount);

    /**
     * 用于商户登录时，查询商户是否审核通过
     * @param phone
     * @return
     */
    int findBusinessShop(@Param(value = "phone") String phone);

    /**
     * 用于商户登录时，查询商户是否审核通过
     * @param userId 商户userId
     * @return
     */
    BusinessApply findBusinessShopByUserId(@Param(value = "userId") long userId);

    /**
     * 根据userId找到用户信息
     * @param userId
     * @return
     */
    UserAccount findById(long userId);

}
