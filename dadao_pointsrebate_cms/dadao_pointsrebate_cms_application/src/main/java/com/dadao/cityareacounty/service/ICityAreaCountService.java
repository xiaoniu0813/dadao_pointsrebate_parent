package com.dadao.cityareacounty.service;

import com.dadao.city.entity.CityAreaCountyPO;

import java.util.List;

/**
 * Created by NFY on 2017-11-13.
 */
public interface ICityAreaCountService {

    List findAllProvinceList();

    List findLowerCityById(CityAreaCountyPO cityAreaCountyPO);
}
