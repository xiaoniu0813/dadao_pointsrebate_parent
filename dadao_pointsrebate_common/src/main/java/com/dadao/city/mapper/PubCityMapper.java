package com.dadao.city.mapper;

import com.dadao.city.entity.PubCity;

import java.util.List;

/**
 * Created by yangrui on 2017/7/16.
 */
public interface PubCityMapper {
    List findByPid(int pid);

    PubCity findById(int id);
}
