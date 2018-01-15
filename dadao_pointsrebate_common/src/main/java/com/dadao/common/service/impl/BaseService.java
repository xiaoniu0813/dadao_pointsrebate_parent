package com.dadao.common.service.impl;

import com.dadao.common.mapper.IBaseMapper;
import com.dadao.common.service.IBaseService;
import com.dadao.utils.Page;
import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;

import java.io.Serializable;
import java.util.List;

/**
 * Created by e on 2017-07-23.
 */
public class BaseService implements IBaseService {

    private IBaseMapper baseMapper;

    public IBaseMapper getBaseMapper() {
        return baseMapper;
    }

    public void setBaseMapper(IBaseMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    /**
     * 插入对象
     *
     * @param obj
     * @return
     */
    public Result insert(Object obj) {
        Result result = null;
        Integer res = this.getBaseMapper().insert(obj);

        if(res == 1){
            result = new Result("1000");
        }else{
            result = new Result("-1");
        }

        return result;
    }

    /**
     * 根据id删除对象（逻辑删除）
     *
     * @param id
     * @return
     */
    public Result deleteById(Serializable id) {
        Result result = null;
        Integer res = this.getBaseMapper().deleteById(id);

        if(res == 1){
            result = new Result("1000");
        }else{
            result = new Result("-1");
        }

        return result;
    }

    /**
     * 更新对象
     *
     * @param obj
     * @return
     */
    public Result update(Object obj) {
        Result result = null;
        Integer res = this.getBaseMapper().update(obj);

        if(res == 1){
            result = new Result("1000");
        }else{
            result = new Result("-1");
        }

        return result;
    }

    /**
     * 根据id获取对象
     *
     * @param id
     * @return
     */
    public Object getById(Serializable id) {
        return this.getBaseMapper().getById(id);
    }

    /**
     * 根据对象 获取列表
     *
     * @param obj
     * @return
     */
    public List list(Object obj) {
        return this.getBaseMapper().list(obj);
    }

    /**
     * 根据对象 统计数
     *
     * @param obj
     * @return
     */
    public Integer count(Object obj) {
        return this.getBaseMapper().count(obj);
    }

    /**
     * 根据分页 获取数据
     *
     * @param Page
     * @return
     */
    public QueryResult getByPage(Page page) {
        QueryResult queryResult = new QueryResult();
        Integer totalCount = this.getBaseMapper().count(page);
        List list = this.getBaseMapper().list(page);

        queryResult.setList(list);
        queryResult.setTotalSize(Long.valueOf(totalCount));
        return queryResult;
    }
}
