package com.dadao.common.service;

import com.dadao.utils.Page;
import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;

import java.io.Serializable;
import java.util.List;

/**
 * Created by e on 2017-07-23.
 */
public interface IBaseService {

    /**
     * 插入对象
     * @return
     */
    Result insert(Object obj);

    /**
     * 根据id删除对象（逻辑删除）
     * @param id
     * @return
     */
    Result deleteById(Serializable id);

    /**
     * 更新对象
     * @return
     */
    Result update(Object obj);

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


    /**
     * 根据分页 获取数据
     * @param Page
     * @return
     */
    QueryResult getByPage(Page page);
}
