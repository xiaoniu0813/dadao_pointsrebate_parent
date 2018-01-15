package com.dadao.pub.service;

import com.dadao.category.entity.Category;
import com.dadao.city.entity.CityAreaCountyPO;
import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;

import java.util.List;

/**
 * Created by yangrui on 2017/7/16.
 */
public interface PubService {
    List findByPid(int pid);

    QueryResult findByPage(Category category);

    List findAllCityList();

    Result findLowerCity(CityAreaCountyPO cityAreaCountyPO);

}
