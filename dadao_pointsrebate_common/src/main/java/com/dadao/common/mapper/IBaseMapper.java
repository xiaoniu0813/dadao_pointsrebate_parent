package com.dadao.common.mapper;

import java.io.Serializable;
import java.util.List;

/**
 * Created by e on 2017-07-23.
 */
public interface IBaseMapper {

    /**
     * 插入对象
     * @return
     */
    Integer insert(Object obj);

    /**
     * 根据id删除对象（逻辑删除）
     * @param id
     * @return
     */
    Integer deleteById(Serializable id);

    /**
     * 更新对象
     * @return
     */
    Integer update(Object obj);

    /**
     * 根据id获取对象
     * @param id
     * @return
     */
    Object getById(Serializable id);

    //list && count 可以结合起来做分页

    /**
     * 根据对象 获取列表
     * @param obj
     * @return
     */
    List list(Object obj);

    /**
     * 根据对象 统计数
     * @param obj
     * @return
     */
    Integer count(Object obj);
}
