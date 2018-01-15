package com.dadao.order.mapper;

import com.dadao.order.entity.ConsumptionRecording;
import com.dadao.pub.mapper.BaseMapper;

/**
 * Created by NFY on 2017-08-01.
 */
public interface IOrderMapper extends BaseMapper {
    ConsumptionRecording findConsumptionRecordingInfo(ConsumptionRecording consumptionRecording);
}
