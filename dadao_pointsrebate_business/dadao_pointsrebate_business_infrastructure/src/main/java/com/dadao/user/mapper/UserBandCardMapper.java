package com.dadao.user.mapper;

import com.dadao.pub.mapper.BaseMapper;

public interface UserBandCardMapper extends BaseMapper {

    /**
     * @Author: yangrui
     * @Description: 解绑银行卡
     * @Date: 下午2:06 2017/8/13
     */
    int updateStatus(Long cardId);
}
