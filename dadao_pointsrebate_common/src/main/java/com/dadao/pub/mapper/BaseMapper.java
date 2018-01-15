package com.dadao.pub.mapper;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yangrui on 2017/7/11.
 */
public interface BaseMapper {

    int save(Object entity);

    int update(Object entity);

    Object findById(Serializable id);

    List findByEntity(Object entity);

    /**
     * @Author: yangrui
     * @Description: 分页
     * @Date: 下午2:00 2017/7/13
     */
    List findByPage(Object entity);

    Long findCount(Object entity);

}
