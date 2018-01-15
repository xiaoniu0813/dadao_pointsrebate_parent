package com.dadao.pub.service.impl;

import com.dadao.activities.pub.activity.PubActivity;
import com.dadao.category.entity.Category;
import com.dadao.city.entity.CityAreaCountyPO;
import com.dadao.pub.service.PubService;
import com.dadao.utils.QueryResult;
import com.dadao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yangrui on 2017/7/16.
 */
@Service
public class PubServiceImpl implements PubService {

    @Autowired
    private PubActivity pubActivity;

    @Override
    @Cacheable(value = "localCache", key = "#pid")
    public List findByPid(int pid) {
        return pubActivity.findByPid(pid);
    }

    @Override
    public QueryResult findByPage(Category category) {
        return pubActivity.findByPage(category);
    }

    public List findAllCityList(){
        return pubActivity.findAllCityList();
    }

    public Result findLowerCity(CityAreaCountyPO cityAreaCountyPO) {
        return pubActivity.findLowerCity(cityAreaCountyPO);
    }
}
