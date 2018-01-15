package com.dadao.user.mapper;

import com.dadao.pub.mapper.BaseMapper;
import com.dadao.user.entity.UserIntegralRecordingPO;

public interface UserIntegralRecordingMapper extends BaseMapper{

    Object findByObject(UserIntegralRecordingPO userIntegralRecordingPO);

}
