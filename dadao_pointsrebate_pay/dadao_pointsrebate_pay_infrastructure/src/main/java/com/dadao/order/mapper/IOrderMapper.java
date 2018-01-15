package com.dadao.order.mapper;

import com.dadao.order.entity.OrderVO;
import com.dadao.pub.mapper.BaseMapper;

/**
 * Created by NFY on 2017-11-20.
 */
public interface IOrderMapper extends BaseMapper {
    OrderVO findByCchannelSequence(String channelSequence);
}
