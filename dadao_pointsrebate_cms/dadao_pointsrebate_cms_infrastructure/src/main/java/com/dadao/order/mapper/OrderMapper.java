package com.dadao.order.mapper;

import com.dadao.pub.mapper.BaseMapper;

import java.util.List;

public interface OrderMapper extends BaseMapper {

    /**
     * 分页查询
     * @param object
     * @return
     */
    List findByPage(Object object);

    /**
     * 根据条件查询总数
     * @param object
     * @return
     */
    Long findTotalSize(Object object);

}
